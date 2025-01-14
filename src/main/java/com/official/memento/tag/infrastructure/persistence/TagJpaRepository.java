package com.official.memento.tag.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {
}
