package com.example.soleproject.service;

import com.example.soleproject.dto.MessageForm;
import com.example.soleproject.entity.Message;
import com.example.soleproject.member.Member;
import com.example.soleproject.member.MemberRepository;
import com.example.soleproject.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Message messageWrite(MessageForm messageForm) {
        String receiverMemberName = messageForm.getReceiverMemberName();
        String senderMemberName = messageForm.getSenderMemberName();
        log.info("receiverMemberName = {}",receiverMemberName);
        log.info("senderMemberName = {}",senderMemberName);

        Member receiver = memberRepository.findByName(messageForm.getReceiverMemberName());
        Member sender = memberRepository.findByName(messageForm.getSenderMemberName());
        log.info("aareceiver = {}",receiver);
        log.info("bbreceiver = {}",sender);


        Message message = new Message();
        message.messageSave(sender,receiver,messageForm.getTitle(),messageForm.getBody());

        messageRepository.save(message);
        return message;
    }

    @Transactional(readOnly = true)
    public List<Message> receivedMessage(Member member) {
        List<Message> messages = messageRepository.findAllByReceiverMember(member);
        return messages;
    }
}
