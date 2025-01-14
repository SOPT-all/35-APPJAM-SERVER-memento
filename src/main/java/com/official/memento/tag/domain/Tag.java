package com.official.memento.tag.domain;

import com.official.memento.tag.domain.enums.TagColor;

public class Tag {
    private Long id;
    private String name;
    private TagColor color;
    private Long memberId;

    public static Tag of(String name, TagColor color, Long memberId) {
        return new Tag(name, color, memberId);
    }

    public static Tag withId(Long id, String name, TagColor color, Long memberId) {
        return new Tag(id, name, color, memberId);
    }

    private Tag(Long id, String name, TagColor color, Long memberId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.memberId = memberId;
    }

    private Tag(String name, TagColor color, Long memberId) {
        this.name = name;
        this.color = color;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TagColor getColor() {
        return color;
    }

    public Long getMemberId() {
        return memberId;
    }
}
