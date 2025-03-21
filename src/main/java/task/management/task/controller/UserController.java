package task.management.task.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import task.management.task.entity.User;
import task.management.task.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 一覧取得
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 検索
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String user_name) {
        return userRepository.findByUserNameContaining(user_name);
    }

    // 単一取得
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 登録
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // 更新
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUser_name(updatedUser.getUser_name());
                    user.setMemo(updatedUser.getMemo());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
