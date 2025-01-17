package com.official.memento.tag.domain;

import java.util.List;

public interface TagRepository {
    Tag save(Tag tag);
    Tag findById(Long id);
    List<Tag> findAllByMemberId(Long memberId);
}
