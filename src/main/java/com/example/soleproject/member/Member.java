package com.example.soleproject.member;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty( message = "아이디를 입력해주세요.")
    @Column
    @Length(min = 3, max = 15 , message = "아이디의 길이는 3자 이상 15자 이하입니다.")
    private String loginId;
    @NotEmpty( message = "이름을 입력해주세요.")
    @Column
    private String name;
    @NotEmpty( message = "비밀번호를 입력해주세요.")
    @Column
//    @Length(min = 5, max = 20, message = "비밀번호의 길이는 5자 이상 20자 이하입니다.")
//    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{3,20}$",  message = "비밀번호는 특수문자를 사용할 수 없습니다.")
    private String password;
    @NotEmpty( message = "닉네임을 입력해주세요.")
    @Column
    @Length(min = 2, max = 7, message = "닉네임의 길이는 2자 이상 7자 이하입니다.")
    private String nickname;
    @NotEmpty( message = "질문에 대한 답을 입력해주세요.")
    @Column
    private String question;

}
