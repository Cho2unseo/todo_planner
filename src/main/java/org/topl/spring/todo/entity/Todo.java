package org.topl.spring.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.topl.spring.user.entity.User;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
@Getter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private Boolean isDone;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column(length = 500)
    private String memo;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo(String content, LocalDate startDate, LocalDate endDate, String memo, User user) {
        this.content = content;
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memo = memo;
        this.user = user;
    }
}
