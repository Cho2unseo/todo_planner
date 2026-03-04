package org.topl.spring.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.topl.spring.common.Timestamped;
import org.topl.spring.todo.dto.request.UpdateTodoRequest;
import org.topl.spring.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Getter
@NoArgsConstructor
public class Todo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private Boolean isDone;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private LocalDateTime actualStartTime;

    @Column
    private LocalDateTime actualEndTime;

    @Column(length = 500)
    private String memo;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo(String content, LocalDateTime startTime, LocalDateTime endTime, String memo, User user) {
        this.content = content;
        this.isDone = false;
        this.startTime = startTime;
        this.endTime = endTime;
        this.memo = memo;
        this.user = user;
    }

    public void update(UpdateTodoRequest request) {
        if (request.content() != null) this.content = request.content();
        if (request.isDone() != null) this.isDone = request.isDone();
        if (request.startTime() != null) this.startTime = request.startTime();
        if (request.endTime() != null) this.endTime = request.endTime();
        if (request.actualStartTime() != null) this.actualStartTime = request.actualStartTime();
        if (request.actualEndTime() != null) this.actualEndTime = request.actualEndTime();
        if (request.memo() != null) this.memo = request.memo();
    }
}
