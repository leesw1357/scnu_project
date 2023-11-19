package com.example.soleproject.entity;

import com.example.soleproject.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Member sendMember;

    @ManyToOne
    @JoinColumn
    private Member receiverMember;

    @Column(nullable = false)
    private String title;
    private String body;


    public void messageSave(Member senderMember,Member receiverMember,
                            String title,String body) {
        this.sendMember = senderMember;
        this.receiverMember = receiverMember;
        this.title = title;
        this.body = body;
    }
}