package com.official.memento.tag.domain;

public interface TagRepository {
    Tag save(Tag tag);
    Tag findById(Long id);
}
