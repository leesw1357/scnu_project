package com.example.soleproject.controller;

import com.example.soleproject.SessionConst;
import com.example.soleproject.dto.MessageForm;
import com.example.soleproject.entity.Message;
import com.example.soleproject.member.Member;
import com.example.soleproject.member.MemberRepository;
import com.example.soleproject.service.MessageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MemberRepository memberRepository;
    private final MessageService messageService;

    @PostMapping("/scnu/message")
    public String messageSend(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,String name, Model model) {
        Member ByName = memberRepository.findByName(name);
        model.addAttribute("receiverMember", ByName);
        model.addAttribute("senderMember", member);
        model.addAttribute("message",new Message());
        return "/scnu/message/send";
    }

    @PostMapping("/scnu/receiver")
    public String messageReceiver(String receiverMember,String senderMember,String title,String body ) {
        log.info("receivasderMember ={}, senderMember = {}",receiverMember,senderMember);
        MessageForm messageForm = new MessageForm();
        messageForm.setReceiverMemberName(receiverMember);
        messageForm.setSenderMemberName(senderMember);
        messageForm.setTitle(title);
        messageForm.setBody(body);

        Message message = messageService.messageWrite(messageForm);

        return "/scnu/board/main";
    }
}
