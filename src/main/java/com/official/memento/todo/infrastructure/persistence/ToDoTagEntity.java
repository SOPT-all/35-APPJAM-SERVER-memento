package com.official.memento.todo.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "todo_tag")
public class ToDoTagEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long todoId;
}
