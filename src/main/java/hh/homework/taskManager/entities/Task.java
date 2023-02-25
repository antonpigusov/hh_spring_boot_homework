package hh.homework.taskManager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "taskid")
    private UUID id;
    @Column(name = "taskname")
    private String taskName;
    @Column(name = "creationdate")
    private LocalDate creationDate;
    @Column(name = "deadline")
    private LocalDate deadline;
    @Column(name = "status")
    private String status;
    public Task(){}
    public Task(String taskName, LocalDate creationDate, LocalDate deadline, String status) {
        this.taskName = taskName;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.status = status;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", creationDate=" + creationDate +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                '}';
    }
}
