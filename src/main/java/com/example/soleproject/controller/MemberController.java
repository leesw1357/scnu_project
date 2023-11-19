package com.example.soleproject.controller;

import com.example.soleproject.login.LoginForm;
import com.example.soleproject.member.Member;
import com.example.soleproject.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/signup";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "members/signup";
        }
        Member byLoginId = memberRepository.findByLoginId(member.getLoginId());
        log.info("member={}", member);
        memberRepository.save(member);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute LoginForm form, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            model.addAttribute("member", form);
            return "login/idResult";
        }
        Member byLoginId = memberRepository.findByLoginId(form.getLoginId());
        byLoginId.setPassword(form.getPassword());
        memberRepository.save(byLoginId);
        log.info("byLoginId = {}",byLoginId);
        log.info("form = {}",form);
        return "redirect:/";
    }
}