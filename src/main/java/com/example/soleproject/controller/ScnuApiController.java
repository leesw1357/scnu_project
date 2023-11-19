package com.example.soleproject.controller;

import com.example.soleproject.dto.ClubForm;
import com.example.soleproject.entity.ClubScnu;
import com.example.soleproject.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScnuApiController {
    @Autowired
    private ClubService clubService;


    @GetMapping("/api/scnu/{id}")
    public ClubScnu show(@PathVariable Long id) {
        return clubService.FindByIdForApi(id);
    }




    @GetMapping("/scnu/api/scnu")
    public List<ClubScnu> index() {
        return clubService.indexForApi();
    }
}
