package com.official.memento.todo.domain;

public class ToDoTag {
    private Long id;
    private long tagId;
    private long toDoId;

    public ToDoTag(final Long id, final long tagId, final long toDoId) {
        this.id = id;
        this.tagId = tagId;
        this.toDoId = toDoId;
    }

    public ToDoTag(final long tagId, final long toDoId) {
        this.tagId = tagId;
        this.toDoId = toDoId;
    }

    public static ToDoTag withId(
            final Long id,
            final long tagId,
            final long toDoId
    ) {
        return new ToDoTag(id, tagId, toDoId);
    }

    public static ToDoTag of(
            final long tagId,
            final long toDoId
    ) {
        return new ToDoTag(tagId, toDoId);
    }

    public Long getId() {
        return id;
    }

    public long getTagId() {
        return tagId;
    }

    public long getToDoId() {
        return toDoId;
    }
}
