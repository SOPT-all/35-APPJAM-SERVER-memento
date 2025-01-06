package com.official.memento.member.application;

import com.official.memento.member.application.command.MemberNicknameUpdateCommand;
import com.official.memento.member.domain.Member;
import com.official.memento.member.infrastructure.MemberDomainRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDomainRepository memberDomainRepository;

    public MemberService( MemberDomainRepository memberDomainRepository) {
        this.memberDomainRepository = memberDomainRepository;
    }

    public void updateNickname(MemberNicknameUpdateCommand command) {
        Member member = memberDomainRepository.findById(1L);
        memberDomainRepository.updateNickname(member, command.nickname());
    }

}
