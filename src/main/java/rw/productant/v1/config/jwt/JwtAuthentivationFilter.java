package rw.productant.v1.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rw.productant.v1.common.exceptions.JWTVerificationException;
import rw.productant.v1.common.exceptions.TokenException;
import rw.productant.v1.user.services.UserSecurityDetails;
import rw.productant.v1.user.services.UserSecurityDetailsService;
import rw.productant.v1.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWTAuthFilter intercepts incoming requests to validate JWT tokens and authenticate users.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthentivationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserSecurityDetailsService userSecurityDetailsService;

    /**
     * Handles and throws TokenException with appropriate HTTP response.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain.
     * @param e The exception that occurred.
     * @throws IOException If an I/O error occurs.
     * @throws ServletException If a servlet error occurs.
     */
    public void throwErrors(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Exception e) throws IOException, ServletException {
        TokenException exception = new TokenException(e.getMessage());

        // Set response status and content type
        response.setStatus(exception.getResponseEntity().getStatusCodeValue());
        response.setContentType("application/json");

        // Write response body as JSON
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(exception.getResponseEntity().getBody()));

        // Exit the filter chain
        filterChain.doFilter(request, response);
    }

    /**
     * Filters incoming requests to authenticate using JWT tokens.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain.
     * @throws ServletException If a servlet error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        JwtUserInfo jwtUserInfo = null;
        String jwtToken = null;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);

        try {
            jwtUserInfo = jwtUtils.decodeToken(jwtToken);
        } catch (JWTVerificationException e) {
            throwErrors(request, response, filterChain, e);
            return;
        }

        if (jwtUserInfo.getEmail() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserSecurityDetails userSecurityDetails = (UserSecurityDetails) userSecurityDetailsService.loadUserByUsername(jwtUserInfo.getEmail());
                if (jwtUtils.isTokenValid(jwtToken, userSecurityDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userSecurityDetails, jwtToken, userSecurityDetails.getGrantedAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                filterChain.doFilter(request, response);
            } catch (UsernameNotFoundException e) {
                throwErrors(request, response, filterChain, e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
