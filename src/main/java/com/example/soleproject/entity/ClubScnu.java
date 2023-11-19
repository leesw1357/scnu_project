package com.example.soleproject.entity;

import com.example.soleproject.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class ClubScnu extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String category;
    @Column
    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    @Column
    private String startdate;
    @Column
    private String finaldate ;
    @Column
    private String place ;
    @Column
    private String info;
    @Column
    private String filename;
    @Column
    private String filepath;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @ManyToOne
    @JoinColumn
    private Member member;
    @OneToMany
    @Column
    private List<Comment> comment;
    @Column
    private int recommendationCount;


    public void addRecommendation() {
        this.recommendationCount++;
    }



}
