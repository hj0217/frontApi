package com.demo1.demo1.member.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Member {


    private int id; //시퀀스 , auto_increment
    private String username;
    private String password;
    private String email;
    private String role; //ROLE_USER, ROLE_ADMIN  //

}
