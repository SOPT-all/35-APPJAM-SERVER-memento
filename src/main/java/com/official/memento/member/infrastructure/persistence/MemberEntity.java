package com.official.memento.member.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// database에 들어가는 table을 그저 객체로 표현한 결과물
// 사용자 data임.
@Entity
public class MemberEntity {

    // 캡슐화. private 을 써서 외부에서 접근하지 못하게 한다.
    // 객체지향 내용을 공부하시면 됩니다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String 닉네임;

    private Integer age;

    public MemberEntity() {
    }

    public MemberEntity(Long id, String 닉네임) {
        this.id = id;
        this.닉네임 = 닉네임;
    }

    public MemberEntity(String nickname, Integer age) {
        this.닉네임 = nickname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return 닉네임;
    }

    public Integer getAge() {
        return age;
    }

    public void updateNickname(String nickname) {
        this.닉네임 = nickname;
    }

}
