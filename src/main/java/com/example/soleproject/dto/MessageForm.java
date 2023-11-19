package com.example.soleproject.dto;

import com.example.soleproject.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageForm {

    private String senderMemberName;
    private String receiverMemberName;
    private String title;
    private String body;

}