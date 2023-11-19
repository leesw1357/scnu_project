package com.example.soleproject.controller;

import com.example.soleproject.SessionConst;
import com.example.soleproject.member.Member;
import com.example.soleproject.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class MapController {
    private final ClubRepository clubRepository;


    @GetMapping("/scnu/main")
    public String ScnuMap(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {

        model.addAttribute("member", member);
        return "/scnu/board/main";
    }

    @GetMapping("/scnu/main/club")
    public String ScnuClubMap(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        model.addAttribute("member", member);
        return "/scnu/map/clubMainMap";
    }
    @GetMapping("/scnu/main/extracurricular")
    public String ScnuExtracurricularMap(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        model.addAttribute("member", member);
        return "/scnu/map/extracurricularMainMap";
    }
    @GetMapping("/scnu/main/school")
    public String ScnuSchoolMap(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        model.addAttribute("member", member);
        return "/scnu/map/school";
    }
    @GetMapping("/scnu/main/student")
    public String ScnuStudentMap(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        model.addAttribute("member", member);
        return "/scnu/map/student";
    }
    @GetMapping("/scnu/main/scholarship")
    public String ScnuScholarshipMap(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        model.addAttribute("member", member);
        return "/scnu/map/scholarship";
    }



}