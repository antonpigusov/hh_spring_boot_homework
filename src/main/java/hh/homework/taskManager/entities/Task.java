package hh.homework.taskManager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskid")
    private Integer id;
    @Column(name = "taskname", length = 250)
    private String taskName;
    @Column(name = "creationdate")
    private LocalDate creationDate;
    @Column(name = "deadline")
    private LocalDate deadline;
    @Column(name = "status", length = 50)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    public Task(){}
    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.creationDate = LocalDate.now();
        this.deadline = deadline;
        this.status = TaskStatus.CREATED;
    }
}
