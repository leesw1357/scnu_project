package com.example.soleproject.service;

import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.member.Member;
import com.example.soleproject.repository.ClubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j

public class ClubService {

    @Autowired
    private ClubRepository clubRepository;


    public void write(ClubScnu clubScnu, MultipartFile file) throws Exception{
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
        clubRepository.save(clubScnu);

    }


    public void ifNoFileCreate(ClubScnu clubScnu){
        clubScnu.setStartdate(clubScnu.getStartdate().replaceAll("T"," "));
        clubScnu.setFinaldate(clubScnu.getFinaldate().replaceAll("T"," "));
        clubRepository.save(clubScnu);

    }

    public String update(ClubScnu clubScnu, MultipartFile file, Member member) throws IOException {

        ClubScnu target = clubRepository.findById(clubScnu.getId()).orElse(null);


        if (target != null) {
            if (file.isEmpty() == true) {
                clubScnu.setStartdate(clubScnu.getStartdate().replaceAll("T"," "));
                clubScnu.setFinaldate(clubScnu.getFinaldate().replaceAll("T"," "));
                clubScnu.setMember(member);
                clubRepository.save(clubScnu);
                String page = "redirect:/scnu/club";
                return page;
            } else {
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
                String page = "redirect:/scnu/club";
                return page;
            }
        }
        String page = "redirect:/scnu/club";
        return page;
    }
    public String delete(Long id, RedirectAttributes rttr ) {
        ClubScnu target = clubRepository.findById(id).orElse(null);

        if (target != null) {
            clubRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제 완료");
            String page = "redirect:/scnu/club";
            return page;
        }
        String page = "redirect:/scnu/club";
        return page;
    }


    public ClubScnu FindByIdForApi(Long id) {
        ClubScnu clubScnu = clubRepository.findById(id).orElse(null);
        return clubScnu;
    }

    public List<ClubScnu> indexForApi() {
        return clubRepository.findAll();
    }
}