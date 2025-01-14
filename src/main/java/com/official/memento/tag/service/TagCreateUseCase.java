package com.official.memento.tag.service;

import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.service.command.TagCreateCommand;

@FunctionalInterface
public interface TagCreateUseCase {
    Tag create(TagCreateCommand command);
}
