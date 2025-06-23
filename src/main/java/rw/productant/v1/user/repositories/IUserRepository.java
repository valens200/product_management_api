package rw.productant.v1.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.productant.v1.user.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository  extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
