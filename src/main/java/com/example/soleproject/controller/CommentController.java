package com.example.soleproject.controller;

import com.example.soleproject.SessionConst;
import com.example.soleproject.dto.CommentForm;
import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.entity.Comment;
import com.example.soleproject.member.Member;
import com.example.soleproject.repository.ClubRepository;
import com.example.soleproject.repository.CommentRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentRespository commentRespository;
    private final ClubRepository clubRepository;

    @PostMapping("/scnu/comment/{id}")
    public String Comment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, @ModelAttribute CommentForm commentForm, @PathVariable Long id , Model model) {
        ClubScnu clubScnu = clubRepository.findById(id).orElse(null);
        commentForm.setClubScnu(clubScnu);
        Comment comment = new Comment();
        comment.saveComment(member,commentForm.getClubScnu(),commentForm.getWriterName(),commentForm.getBody());
        commentRespository.save(comment);

        ClubScnu scnu = clubRepository.findById(id).orElse(null);
        ArrayList<Comment> allComment = commentRespository.findByClubScnu(scnu);

        model.addAttribute("com", comment);
        model.addAttribute("scnu", scnu);
        model.addAttribute("member", member);
        model.addAttribute("comment", allComment);

        if(scnu.getMember().getId() == member.getId()){
            model.addAttribute("delete", member);
        }
        return "redirect:/scnu/"+scnu.getCategory() +"/"+scnu.getId();
    }


    @GetMapping("/scnu/comment/{id}/delete")
    public String CommentDelete(@PathVariable Long id,Model model) {
        Comment comment = commentRespository.findById(id).orElse(null);
        ClubScnu clubScnu = comment.getClubScnu();
        if(comment != null){
            commentRespository.delete(comment);
        }
        return "redirect:/scnu/"+clubScnu.getCategory() +"/"+clubScnu.getId();
    }

    @PostMapping("/scnu/comment/{id}/update")
    public String CommentUpdate(@PathVariable Long id,Model model,CommentForm form){
        log.info("CommentForm = {}", form);
        Comment comment = commentRespository.findById(id).orElse(null);
        comment.setBody(form.getBody());
        ClubScnu clubScnu = comment.getClubScnu();
        commentRespository.save(comment);

        return "redirect:/scnu/"+clubScnu.getCategory() +"/"+clubScnu.getId();
    }

}