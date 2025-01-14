package com.official.memento.tag.infrastructure.persistence;


import com.official.memento.tag.domain.enums.TagColor;
import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class TagEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private TagColor color;
}
