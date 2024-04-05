package jwt.jwt.newJWT.repository;

import jwt.jwt.newJWT.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail (String email);
}
