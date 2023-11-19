package com.example.soleproject.controller;

import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainApiController {

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping("/api/main/")
    public List<ClubScnu> main(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();

        List<ClubScnu> scnu = clubRepository.findAll().stream()
                .filter(club -> {
                    LocalDateTime startDate = LocalDateTime.parse(club.getStartdate(), formatter);
                    LocalDateTime finalDate = LocalDateTime.parse(club.getFinaldate(), formatter);
                    return now.isAfter(startDate) && now.isBefore(finalDate);
                })
                .collect(Collectors.toList());

        return scnu;

    }

    @GetMapping("/api/main/club")
    public List<ClubScnu> clubMain() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        List<ClubScnu> scnu = clubRepository.findByCategory("club").stream()
                .filter(club -> {
                    LocalDateTime startDate = LocalDateTime.parse(club.getStartdate(), formatter);
                    LocalDateTime finalDate = LocalDateTime.parse(club.getFinaldate(), formatter);
                    return now.isAfter(startDate) && now.isBefore(finalDate);
                })
                .collect(Collectors.toList());

        return scnu;
    }

    @GetMapping("/api/main/extracurricular")
    public List<ClubScnu> extracurricularMain() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        List<ClubScnu> scnu = clubRepository.findByCategory("extracurricular").stream()
                .filter(club -> {
                    LocalDateTime startDate = LocalDateTime.parse(club.getStartdate(), formatter);
                    LocalDateTime finalDate = LocalDateTime.parse(club.getFinaldate(), formatter);
                    return now.isAfter(startDate) && now.isBefore(finalDate);
                })
                .collect(Collectors.toList());

        return scnu;
    }

    @GetMapping("/api/main/school")
    public List<ClubScnu> schoolMain() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        List<ClubScnu> scnu = clubRepository.findByCategory("school").stream()
                .filter(club -> {
                    LocalDateTime startDate = LocalDateTime.parse(club.getStartdate(), formatter);
                    LocalDateTime finalDate = LocalDateTime.parse(club.getFinaldate(), formatter);
                    return now.isAfter(startDate) && now.isBefore(finalDate);
                })
                .collect(Collectors.toList());

        return scnu;
    }

    @GetMapping("/api/main/student")
    public List<ClubScnu> studentMain() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        List<ClubScnu> scnu = clubRepository.findByCategory("studentMain").stream()
                .filter(club -> {
                    LocalDateTime startDate = LocalDateTime.parse(club.getStartdate(), formatter);
                    LocalDateTime finalDate = LocalDateTime.parse(club.getFinaldate(), formatter);
                    return now.isAfter(startDate) && now.isBefore(finalDate);
                })
                .collect(Collectors.toList());

        return scnu;
    }

    @GetMapping("/api/main/scholarship")
    public List<ClubScnu> scholarshipMain() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        List<ClubScnu> scnu = clubRepository.findByCategory("scholarship").stream()
                .filter(club -> {
                    LocalDateTime startDate = LocalDateTime.parse(club.getStartdate(), formatter);
                    LocalDateTime finalDate = LocalDateTime.parse(club.getFinaldate(), formatter);
                    return now.isAfter(startDate) && now.isBefore(finalDate);
                })
                .collect(Collectors.toList());

        return scnu;
    }
}