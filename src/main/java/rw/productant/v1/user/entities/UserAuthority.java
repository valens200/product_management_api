package rw.productant.v1.user.entities;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class UserAuthority implements GrantedAuthority {

    public UUID userId;
    public String authority;

    public UserAuthority(UUID userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
