package task.management.task.repository;

import task.management.task.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.user_name LIKE %:user_name%")
    List<User> findByUserNameContaining(@Param("user_name") String user_name);
}
