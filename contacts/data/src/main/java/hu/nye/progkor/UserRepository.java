package hu.nye.progkor;

import hu.nye.progkor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA interface for user storage.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
