package com.example.soleproject.entity;

import com.example.soleproject.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String writerName;
    @Column
    private String body;
    @ManyToOne
    @JoinColumn
    private ClubScnu clubScnu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    public void saveComment(Member member,ClubScnu clubScnu,String writerName,String body) {
        this.writerName = writerName;
        this.body = body;
        this.member = member;
        this.clubScnu = clubScnu;

    }



}