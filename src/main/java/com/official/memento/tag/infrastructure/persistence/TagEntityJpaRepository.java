package com.official.memento.tag.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagEntityJpaRepository extends JpaRepository<TagEntity, Long> {
}
