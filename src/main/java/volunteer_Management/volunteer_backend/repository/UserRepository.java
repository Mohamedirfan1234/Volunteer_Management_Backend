package volunteer_Management.volunteer_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import volunteer_Management.volunteer_backend.entity.User;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    
    // Add method to find all users with same email (for cleanup)
    List<User> findAllByEmail(String email);
}