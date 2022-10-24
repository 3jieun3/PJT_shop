package jpabook.shop.service;

import jpabook.shop.domain.entity.Member;
import jpabook.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Long findMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseGet(() -> {
            return new Member();
        });
        return member.getId();
    }

    @Transactional
    public Long joinMember(Member member) {
        return memberRepository.save(member).getId();
    }
}
