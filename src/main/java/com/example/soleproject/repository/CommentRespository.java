package com.example.soleproject.repository;

import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.entity.Comment;
import com.example.soleproject.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CommentRespository extends JpaRepository<Comment,Long> {

    @Override
    List<Comment> findAll();

    ArrayList<Comment> findByClubScnu(ClubScnu clubScnu);
    ArrayList<Comment> findByMember(Member member);


}
