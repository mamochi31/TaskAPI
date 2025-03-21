package task.management.task.repository;

import task.management.task.entity.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.task_name LIKE %:task_name%")
    List<Task> findByTaskNameContaining(@Param("task_name") String task_name);
}
