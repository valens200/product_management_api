package rw.productant.v1.user.services;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rw.productant.v1.user.entities.User;
import rw.productant.v1.user.repositories.IUserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserSecurityDetailsService  implements UserDetailsService {
    private final IUserRepository userRepository;
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent()){
            return new UserSecurityDetails(userOptional.get());
        }else{
            throw new UsernameNotFoundException("" + email + " was not found");
        }
    }

}
