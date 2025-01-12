package com.official.memento.todo.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoTagJpaRepository extends JpaRepository<ToDoTagEntity, Long> {
}
