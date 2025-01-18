package com.official.memento.todo.service.command;

public record ToDoDeleteCommand(
        long memberId,
        long toDoId
) {
    public static ToDoDeleteCommand of(final long memberId, final long toDoId) {
        return new ToDoDeleteCommand(memberId, toDoId);
    }
}