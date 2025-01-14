package com.official.memento.tag.service;

import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.domain.TagRepository;
import com.official.memento.tag.service.command.TagCreateCommand;
import org.springframework.stereotype.Service;

@Service
public class TagService implements TagCreateUseCase{

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag create(TagCreateCommand command) {
        // Tag 객체 생성
        final Tag tag = Tag.of(command.name(), command.color(), command.memberId());
        return tagRepository.save(tag);
    }
}
