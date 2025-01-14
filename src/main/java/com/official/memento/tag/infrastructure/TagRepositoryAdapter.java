package com.official.memento.tag.infrastructure;

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
    public Tag save(Tag tag){
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
}
