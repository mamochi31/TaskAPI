package task.management.task.controller;

import org.springframework.web.bind.annotation.*;
import task.management.task.entity.Task;
import task.management.task.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 一覧取得
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // 検索
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String task_name) {
        return taskRepository.findByTaskNameContaining(task_name);
    }

    // 単一取得
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 登録
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 更新
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTask_name(task.getTask_name());
                    existingTask.setStart_date(task.getStart_date());
                    existingTask.setDeadline(task.getDeadline());
                    existingTask.setComplete_flag(task.getComplete_flag());
                    existingTask.setComment(task.getComment());
                    return ResponseEntity.ok(taskRepository.save(existingTask));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
