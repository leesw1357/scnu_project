package com.example.soleproject.controller;


import com.example.soleproject.SessionConst;
import com.example.soleproject.dto.CommentForm;
import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.entity.Comment;
import com.example.soleproject.entity.Recommendation;
import com.example.soleproject.member.Member;
import com.example.soleproject.repository.ClubRepository;
import com.example.soleproject.repository.CommentRespository;
import com.example.soleproject.repository.RecommendationRepository;
import com.example.soleproject.service.ClubService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.security.Principal;
import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final ClubRepository clubRepository;
    private final CommentRespository commentRespository;
    private final RecommendationRepository recommendationRepository;


    //테스트
    @GetMapping("/scnu/chat")
    public String chat(){
        return "/scnu/board/chat";
    }


    /**
     * 앨범 맵핑
     */
    //동아리
    @GetMapping("/scnu/club")
    public String ClubAlbum(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        ArrayList<ClubScnu> club = clubRepository.findByCategory("club");
        Collections.reverse(club);
        model.addAttribute("scnu", club);
        model.addAttribute("category","club");
        model.addAttribute("member", member);
        return "/scnu/board/album";
    }

    // 비교과
    @GetMapping("/scnu/extracurricular")
    public String test1(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,Model model){
        ArrayList<ClubScnu> extracurricular = clubRepository.findByCategory("extracurricular");
        Collections.reverse(extracurricular);
        model.addAttribute("scnu",extracurricular);
        model.addAttribute("category","extracurricular");
        model.addAttribute("member", member);

        return "/scnu/board/album";
    }
    //학교행사
    @GetMapping("/scnu/school")
    public String SchoolAlbum(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,Model model) {
        List<ClubScnu> school = clubRepository.findByCategory("school");
        Collections.reverse(school);
        model.addAttribute("scnu", school);
        model.addAttribute("category","school");
        model.addAttribute("member", member);

        return "/scnu/board/album";
    }

    //학교행사
    @GetMapping("/scnu/student")
    public String StudentAlbum(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,Model model) {
        List<ClubScnu> student = clubRepository.findByCategory("student");
        Collections.reverse(student);
        model.addAttribute("scnu", student);
        model.addAttribute("category","student");
        model.addAttribute("member", member);

        return "/scnu/board/album";
    }

    //장학금
    @GetMapping("/scnu/scholarship")
    public String ScholarshipAlbum(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,Model model) {
        List<ClubScnu> scholarship = clubRepository.findByCategory("scholarship");
        Collections.reverse(scholarship);
        model.addAttribute("scnu", scholarship);
        model.addAttribute("category","scholarship");
        model.addAttribute("member", member);

        return "/scnu/board/album";
    }

    //전체글보기
    @GetMapping("/scnu")
    public String AllPosts(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,Model model) {
        List<ClubScnu> scnu = clubRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("scnu", scnu);
        model.addAttribute("category", "all");
        model.addAttribute("member", member);
        return "/scnu/board/album";
    }


    /**
     * 글 작성
     */
    @GetMapping("/scnu/write")
    public String scnuNew(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,Model model) {
        model.addAttribute("clubScnu", new ClubScnu());
        model.addAttribute("member",member);
        return "/scnu/board/write";
    }


    @PostMapping("/scnu/write")
    public String scnuCreate(@Validated @ModelAttribute ClubScnu clubScnu, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, BindingResult bindingResult, MultipartFile file, Model model) throws Exception {

        if(bindingResult.hasErrors()){
            return "/scnu/board/write";
        }
        model.addAttribute("member", member);


        // 파일이 존재 여부에 따라 저장방식 구분
        if (file.isEmpty() == true) {
            clubScnu.setStartdate(clubScnu.getStartdate().replaceAll("T"," "));
            clubScnu.setFinaldate(clubScnu.getFinaldate().replaceAll("T"," "));
            clubScnu.setMember(member);
            clubRepository.save(clubScnu);
        } else {
            // 프로젝트 경로를 저장함
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";

            //  log.info(file.getOriginalFilename());
            // 랜덤으로 이름만들기
            UUID uuid = UUID.randomUUID();

            // 랜덤이름을 파일이름앞에 붙이고 원래 파일이름을 뒤에 붙인다.
            String fileName = file.getOriginalFilename();

            // prijectPath에 name이라는 이름으로 저장
            File saveFile = new File(projectPath,fileName);

            // 예외처리 해줘야함 파일 저장
            file.transferTo(saveFile);

            // 파일이름
            clubScnu.setFilename(fileName);
            // 파일 경로 이름
            clubScnu.setFilepath("/img/" + fileName);


            clubScnu.setStartdate(clubScnu.getStartdate().replaceAll("T"," "));
            clubScnu.setFinaldate(clubScnu.getFinaldate().replaceAll("T"," "));
            clubScnu.setMember(member);
            clubRepository.save(clubScnu);
        }


        return "redirect:/scnu/"+ clubScnu.getCategory();

    }


    /**
     *게시판 상세보기
     */

    @GetMapping("scnu/{category}/{id}")
    public String scnuView(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,@PathVariable Long id ,Model model) {
        ClubScnu scnu = clubRepository.findById(id).orElse(null);
        ArrayList<Comment> allComment = commentRespository.findByClubScnu(scnu);
        model.addAttribute("scnu", scnu);
        model.addAttribute("member", member);
        model.addAttribute("comment", allComment);
        if(scnu.getMember().getId() == member.getId()){
            model.addAttribute("delete", member);
        }
        return "/scnu/board/view";
    }




    @GetMapping("/scnu/{category}/{id}/edit")
    public String edit(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, @PathVariable  Long id, Model model) {
        ClubScnu clubScnu = clubRepository.findById(id).orElse(null);
        model.addAttribute("clubScnu", clubScnu);
        model.addAttribute("member",member);

        return "/scnu/board/edit";
    }

    @PostMapping("/scnu/club/update")
    public String scnuUpdate(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,@ModelAttribute ClubScnu clubScnu, MultipartFile file,Model model) throws Exception {
        model.addAttribute("member", member);

        return clubService.update(clubScnu, file, member);
    }

    @GetMapping("/scnu/club/{id}/delete")
    public String delete(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, @PathVariable Long id, Model model) {
        ClubScnu clubScnu = clubRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        if (clubScnu != null) {

            //글 삭제시 댓글도 삭제
            ArrayList<Comment> byClubScnuComment = commentRespository.findByClubScnu(clubScnu);
            commentRespository.deleteAll(byClubScnuComment);
            clubRepository.delete(clubScnu);

            return "redirect:/scnu/club";
        }
        return "redirect:/scnu/club";
    }

    //검색
    @PostMapping("/scnu/{category}/search")
    public String search(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,@RequestParam("title") String title,@PathVariable String category,Model model) {

        log.info("category ={}",category);


        if(category.equals("all")){
            List<ClubScnu> all = clubRepository.findByTitleContaining(title);
            log.info("all ={}",all);
            Collections.reverse(all);
            model.addAttribute("scnu", all);
            model.addAttribute("member",member);
            return "/scnu/board/album";
        }
        else {
            ArrayList<ClubScnu> target = clubRepository.findByTitleContainingAndCategory(title, category);
            Collections.reverse(target);
            log.info("else category ={}",category);
            model.addAttribute("scnu",target);
            model.addAttribute("member",member);


            return "/scnu/board/album";
        }
    }



    /**
     * 내가 쓴글 구현
     */
    @GetMapping("/scnu/{memberId}")
    public String MyWrite(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,@PathVariable Long memberId ,Model model){
        ArrayList<ClubScnu> byMemberId = clubRepository.findByMemberId(memberId);
        model.addAttribute("scnu", byMemberId);
        model.addAttribute("member", member);

        return "/scnu/board/myWriteList";
    }

    /**추천 기능*/
    @PostMapping("/recommend/{id}")
    public String recommend(@PathVariable Long id,@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,Model model) {
        ClubScnu clubScnu = clubRepository.findById(id).orElse(null);

        if (member != null && clubScnu != null) {
            if (!hasUserRecommended(member, clubScnu)) {
                clubScnu.addRecommendation();
                clubRepository.save(clubScnu);
                addRecommendationHistory(member, clubScnu);
            }
        }

        return "redirect:/" + "scnu/" + clubScnu.getCategory() + "/" + clubScnu.getId();
    }
    private boolean hasUserRecommended(Member member, ClubScnu clubScnu) {
        return recommendationRepository.existsByMemberAndClubScnu(member, clubScnu);
    }

    private void addRecommendationHistory(Member member, ClubScnu clubScnu) {
        Recommendation recommendation = new Recommendation();
        recommendation.setMember(member);
        recommendation.setClubScnu(clubScnu);
        recommendationRepository.save(recommendation);
    }

}