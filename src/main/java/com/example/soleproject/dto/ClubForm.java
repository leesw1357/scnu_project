package com.example.soleproject.dto;

import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.entity.Comment;
import com.example.soleproject.member.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
public class ClubForm {
    private Long id;
    private String category;
    private String title;
    private String startdate;
    private String finaldate;
    private String place;
    private String info;

    private String filename;

    private String filepath;

    private String latitude;
    private String longitude;
    private Member member;
    private Comment comment;
    private int recommendationCount;

    public ClubScnu toEntity() {
        return new ClubScnu(id,category,title,startdate,finaldate,place,info,filename,filepath,latitude,longitude,member, (List<Comment>) comment,recommendationCount);
    }
}
