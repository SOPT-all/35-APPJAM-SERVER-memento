package com.official.memento.tag.service;

import com.official.memento.tag.domain.Tag;

import java.util.List;

@FunctionalInterface
public interface TagGetUseCase {
    List<Tag> getTags(Long memberId);
}
