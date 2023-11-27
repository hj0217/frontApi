package com.demo1.demo1.member.service;


import com.demo1.demo1.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestTemplateMemberService {

    private final String backendApiUrl = "http://localhost:8080/api/v1/member";
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /*------------------------------  회원가입  ------------------------------------*/
    public int registerMember(Member member) {

        String registerUrl = backendApiUrl + "/register";
        ResponseEntity<Integer> responseEntity = ApiUtil.sendRequest(registerUrl, member, Integer.class);
        return  responseEntity.getBody() != null ?  responseEntity.getBody() : 0;
    }

    /*------------------------------  로그인  ------------------------------------*/
    public ResponseEntity<Member> loginMember(Member member) {

        String loginUrl = backendApiUrl + "/login";
        return ApiUtil.sendRequest(loginUrl, member, Member.class);
    }

//    /*------------------------------   ID 중복체크  ------------------------------------*/
//    public int idCheck(String id) {
//        return termMapper.idCheck(id);
//}
}
