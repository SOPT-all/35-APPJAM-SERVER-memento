package com.official.memento.todo.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import com.official.memento.schedule.domain.ScheduleTag;
import com.official.memento.schedule.infrastructure.persistence.ScheduleTagEntity;
import com.official.memento.tag.infrastructure.persistence.TagEntity;
import com.official.memento.todo.domain.ToDoTag;
import jakarta.persistence.*;

@Entity
@Table(name = "todo_tag")
public class ToDoTagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tagId;
    private Long toDoId;

    private ToDoTagEntity(final Long id, final Long tagId,final long toDoId){
        this.id=id;
        this.tagId = tagId;
        this.toDoId = toDoId;
    }

    public static ToDoTagEntity of(final ToDoTag toDoTag){
        return new ToDoTagEntity(
                toDoTag.getId(),
                toDoTag.getTagId(),
                toDoTag.getToDoId()
        );
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
