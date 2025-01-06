package com.official.memento.member.presentation.dto;

// HTTP 요청 Body 객체 (클라이언트에서 보내는)
// 클라이언트와의 약속
// API Spec
// 서버에서 어느날 nickname을 영어를 잘못해서 닉네임으로 바꾸고 싶어졌어.
public record MemberUpdateRequest(
        String nickname
) {
}


// API
// Client <-> Server
// REST API
// Separation of Concerns(관심사 분리)

// 서버는 데이터와 비즈니스 로직을 관리할테니
// 클라이언트는 UI와 UX