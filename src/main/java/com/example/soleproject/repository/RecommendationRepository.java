package com.example.soleproject.repository;

import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.entity.Recommendation;
import com.example.soleproject.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {

        boolean existsByMemberAndClubScnu(Member member, ClubScnu clubScnu);
    }
