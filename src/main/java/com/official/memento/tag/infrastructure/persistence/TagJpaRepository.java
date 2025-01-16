package com.official.memento.tag.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {
    @Query(
            value = "SELECT * FROM tag WHERE member_id = :memberId",
            nativeQuery = true
    )
    List<TagEntity> findAllByMemberId(Long memberId);
}
