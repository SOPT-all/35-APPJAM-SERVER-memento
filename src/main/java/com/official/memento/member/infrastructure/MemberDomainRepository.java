package com.official.memento.member.infrastructure;


import com.official.memento.member.domain.Member;
import com.official.memento.member.infrastructure.persistence.MemberEntity;
import com.official.memento.member.infrastructure.persistence.MemberJpaRepository;
import com.official.memento.member.infrastructure.persistence.MemberMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberDomainRepository {
    private final MemberJpaRepository memberJpaRepository;

    public MemberDomainRepository(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    // 조회
    // 실제 데이터를 조회해와 <- database(JPA)
    // MemberEntity -> Member 바꿔. Mapper
    public Member findById(Long id) {
        MemberEntity memberEntity = memberJpaRepository.findById(id).orElseThrow();
        return MemberMapper.toDomain(memberEntity);
    }

    public void save(Member member) {
        System.out.println("MemberDomainRepository.save");
    }

    public void updateNickname(Member member, String nickname) {
        MemberEntity memberEntity = memberJpaRepository.findById(member.getId()).orElseThrow();
        memberEntity.updateNickname(nickname);
        System.out.println("MemberDomainRepository.update");
    }

    public void delete(Member member) {
        System.out.println("MemberDomainRepository.delete");
    }
}
