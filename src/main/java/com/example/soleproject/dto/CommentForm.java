package com.example.soleproject.dto;

import com.example.soleproject.entity.ClubScnu;
import lombok.Data;


@Data
public class CommentForm {

    private String writerName;
    private String body;
    private ClubScnu clubScnu;
}
