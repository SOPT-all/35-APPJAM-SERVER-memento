package com.official.memento.tag.infrastructure;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.stereotype.Adapter;
import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.domain.TagRepository;
import com.official.memento.tag.infrastructure.persistence.TagEntity;
import com.official.memento.tag.infrastructure.persistence.TagJpaRepository;

@Adapter
public class TagRepositoryAdapter implements TagRepository {

    private final TagJpaRepository tagJpaRepository;

    public TagRepositoryAdapter(TagJpaRepository tagJpaRepository) {
        this.tagJpaRepository = tagJpaRepository;
    }

    @Override
    public Tag save(Tag tag) {
        TagEntity entity = TagEntity.of(
                tag.getName(),
                tag.getColor(),
                tag.getMemberId()
        );
        TagEntity savedEntity = tagJpaRepository.save(entity);
        return Tag.withId(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getColor(),
                savedEntity.getMemberId()
        );
    }

    @Override
    public Tag findById(Long id) {
        final TagEntity entity = tagJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY));
        return Tag.withId(
                entity.getId(),
                entity.getName(),
                entity.getColor(),
                entity.getMemberId()
        );
    }
}
