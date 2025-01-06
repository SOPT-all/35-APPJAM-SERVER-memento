package com.official.memento.member.domain;

// Domain 객체
// 사용자 database의 Data X
public class Member {
    private Long id;
    private String nickname;

    public Member() {
    }

    public Member(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Member(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
