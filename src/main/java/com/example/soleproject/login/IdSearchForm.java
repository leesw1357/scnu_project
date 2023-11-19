package com.example.soleproject.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class IdSearchForm {
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String question;
}
