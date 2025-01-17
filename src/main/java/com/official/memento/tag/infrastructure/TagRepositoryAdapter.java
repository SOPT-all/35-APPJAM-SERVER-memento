package com.official.memento.tag.infrastructure;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.stereotype.Adapter;
import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.domain.TagRepository;
import com.official.memento.tag.infrastructure.persistence.TagEntity;
import com.official.memento.tag.infrastructure.persistence.TagEntityJpaRepository;

@Adapter
public class TagRepositoryAdapter implements TagRepository {

    private final TagEntityJpaRepository tagEntityJpaRepository;

    public TagRepositoryAdapter(TagEntityJpaRepository tagEntityJpaRepository) {
        this.tagEntityJpaRepository = tagEntityJpaRepository;
    }

    @Override
    public Tag save(Tag tag) {
        TagEntity entity = TagEntity.of(
                tag.getName(),
                tag.getColor(),
                tag.getMemberId()
        );
        TagEntity savedEntity = tagEntityJpaRepository.save(entity);
        return Tag.withId(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getColor(),
                savedEntity.getMemberId()
        );
    }

    @Override
    public Tag findById(Long id) {
        final TagEntity entity = tagEntityJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY));
        return Tag.withId(
                entity.getId(),
                entity.getName(),
                entity.getColor(),
                entity.getMemberId()
        );
    }
}
