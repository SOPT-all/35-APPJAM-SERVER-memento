package com.official.memento.tag.infrastructure.persistence;


import com.official.memento.tag.domain.enums.TagColor;
import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class TagEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     태그 이름
     */
    private String name;

    /*
     태그 color
     */
    @Enumerated(EnumType.STRING)
    private TagColor color;

    /*
     사용자 id
     */
    private Long memberId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TagColor getColor() {
        return color;
    }

    public Long getMemberId() {
        return memberId;
    }

    private TagEntity(String name, TagColor color, Long memberId) {
        this.name = name;
        this.color = color;
        this.memberId = memberId;
    }

    public TagEntity() {
    }

    public static TagEntity of(String name, TagColor color, Long memberId) {
        return new TagEntity(
                name,
                color,
                memberId
        );
    }
}
