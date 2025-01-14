package com.official.memento.todo.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoJpaRepository extends JpaRepository<ToDoEntity, Long> {
}
