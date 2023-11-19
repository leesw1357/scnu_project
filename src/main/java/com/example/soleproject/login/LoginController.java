package com.example.soleproject.login;

import com.example.soleproject.SessionConst;
import com.example.soleproject.member.Member;
import com.example.soleproject.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/signin";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasErrors()){
            return "login/signin";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        log.info("loginMember={}",loginMember);
        if(loginMember == null) {
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 잘못 입력되었습니다.");
        }

        //로그인 성공 처리

        //세션에 있으면 있는 세션반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);


        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/idSearch")
    public String idSearch(){
        return "login/idSearch";
    }

    @PostMapping("/idResult")
    public String idResult(@ModelAttribute IdSearchForm form, RedirectAttributes rttr, Model model){
        log.info("form={}",form);
        Member byLoginId = memberRepository.findByLoginId(form.getLoginId());
        ArrayList<Member> AllMember = memberRepository.findAll();
        Member target = new Member();
        for (Member member : AllMember) {
            if((member.getLoginId().equals(form.getLoginId())) && (member.getQuestion().equals(form.getQuestion()))){
                target = member;
                log.info("member.getLoginId()={}",member.getLoginId());
                log.info("byLoginId.getLoginId()={}",byLoginId.getLoginId());
                log.info("member.getQuestion()={}",member.getQuestion());
                log.info("byLoginId.getQuestion()={}",byLoginId.getQuestion());
            }
        }
        if(target.getLoginId() == null){
            model.addAttribute("msg", "아이디 및 답변 입력을 잘못했습니다.");
            return "login/idSearch";
        }
        log.info("target = {}", target);
        model.addAttribute("member", target);

        return "login/idResult";
    }


    @GetMapping("/signup")
        public String signup(){
        return "members/signup";
    }
    private void expireCookie(HttpServletResponse response , String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}