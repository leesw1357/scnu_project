package com.example.soleproject;

import com.example.soleproject.member.Member;
import com.example.soleproject.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {

        Member member = new Member();
        member.setLoginId("123");
        member.setPassword("123");
        member.setName("테스트아이디");
        member.setNickname("테스트닉네임");
        member.setQuestion("광주");

        memberRepository.save(member);

        Member member2 = new Member();
        member2.setLoginId("234");
        member2.setPassword("234");
        member2.setName("테스트아이디2");
        member2.setNickname("테스트닉네임2");
        member2.setQuestion("광주");

        memberRepository.save(member2);

    }

}