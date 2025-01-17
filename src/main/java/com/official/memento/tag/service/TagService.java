package com.official.memento.tag.service;

import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.domain.TagRepository;
import com.official.memento.tag.service.command.TagCreateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService implements TagCreateUseCase, TagGetUseCase {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Tag create(final TagCreateCommand command) {
        final Tag tag = Tag.of(command.name(), command.color(), command.memberId());
        return tagRepository.save(tag);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> getTags(Long memberId) {
        return tagRepository.findAllByMemberId(memberId);
    }
}
