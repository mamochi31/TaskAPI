package task.management.task.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task_name;
    private LocalDate start_date;
    private LocalDate deadline;
    private Boolean complete_flag = false;
    private String comment;
}
