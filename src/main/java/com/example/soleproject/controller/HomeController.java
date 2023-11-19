package com.example.soleproject.controller;

import com.example.soleproject.SessionConst;
import com.example.soleproject.login.LoginForm;
import com.example.soleproject.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@Valid @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember, Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("loginForm", new LoginForm());

        //세션에 회원 데이터가 없으면
        if (loginMember == null) {
            return "/login/signin";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "/scnu/board/main";
    }
}
