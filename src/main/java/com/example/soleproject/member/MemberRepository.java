package com.example.soleproject.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByLoginId(String loginId);

    ArrayList<Member>findAll();


    Member findByName(String name);


}
