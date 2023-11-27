package com.demo1.demo1.member;


import com.demo1.demo1.common.dto.ErrorObject;
import com.demo1.demo1.member.domain.Member;
import com.demo1.demo1.member.service.RestTemplateMemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


@Controller
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final RestTemplateMemberService restTemplateMemberService;


    /*-----------------------------------회원가입-----------------------------------------*/
    @PostMapping(value = "/memberRegister")
    public ResponseEntity<?> memberRegister(@Valid @RequestBody Member member, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            List<ErrorObject> errors = new ArrayList<>();
            ErrorObject errorObject = new ErrorObject();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {

                errorObject.setField(fieldError.getField());
                errorObject.setMessage(fieldError.getDefaultMessage());
                errors.add(errorObject);
            }
            return ResponseEntity.badRequest().body(errors);
        }

        int memberId = restTemplateMemberService.registerMember(member);
        if (memberId > 0) {
            return ResponseEntity.ok().body(memberId);
        } else {
            ErrorObject errorObject = new ErrorObject();
            errorObject.setField("회원가입 실패");
            errorObject.setMessage("다시 시도해주세요.");
            return ResponseEntity.ok().body(errorObject);
        }

    }


    /*-----------------------------------로그인-----------------------------------------*/
    @PostMapping(value = "/loginAction")
    @ResponseBody //viewResolver 호출x -> 브라우저에 바로 응답함. // return type이 ResponseEntity이면 @Responsebody 어노테이션 생략 가능
    public ResponseEntity<?> login(@Valid @RequestBody Member member, BindingResult bindingResult, HttpServletRequest request) throws LoginException { //BindingResult는 @ModelAttribute 없이도 model에 자동 추가됨.
System.out.println(member.toString());

        if (bindingResult.hasErrors()) { //유효성 검사 에러 발생 시 api server에 요청x 에러메시지 바로 리턴

            List<ErrorObject> errors = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                ErrorObject errorObject = new ErrorObject();
                errorObject.setField(fieldError.getField());
                errorObject.setMessage(fieldError.getDefaultMessage());
                errors.add(errorObject);
            }
            return ResponseEntity.ok().body(errors);
        }

        ResponseEntity<Member> result = restTemplateMemberService.loginMember(member);
             if (!Objects.isNull(result.getBody()) ) { //로그인 성공
                 //return ResponseEntity.ok().body("로그인 성공!!");
                 return ResponseEntity.ok("success");
             } else { // 입력 정보 오류
                 ErrorObject errorObject = new ErrorObject();
                 errorObject.setField("failed");
                 errorObject.setMessage("아이디 혹은 패스워드를 다시 확인해주세요.");
                 return ResponseEntity.ok().body(errorObject); // 아이디,비밀번호 오류
             }
    }


    /*
    *
    * View 반환
    *
    * */

    /*-----------------------------------회원가입-----------------------------------------*/
    @GetMapping(value = "/signup")
    public String signup() {
        return "/member/signup";
    }

    /*-----------------------------------로그인-----------------------------------------*/
    @GetMapping(value = "/login")
    public String login() {
        return "/member/login";
    }

    /*-----------------------------------셋팅-----------------------------------------*/
    @GetMapping(value = "/setting")
    public String setting() {
        return "/member/setting";
    }


}// end

    /*-----------------------------------로그아웃-----------------------------------------*/
//    @GetMapping(value = "/logout")
//    public String logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "redirect:/terms/";
//    }

    /*-----------------------------------ID 중복체크 -----------------------------------------*/
//    @PostMapping(value= "/idCheck")
//    @ResponseBody
//    public ResponseEntity<ErrorObject> idCheck(@RequestBody @Valid String id, BindingResult bindingResult) {
//        int checkedID = termService.idCheck(id);
//        ErrorObject errorObject = new ErrorObject();
//
//        if(checkedID <= 0) { // 사용가능 ID
//                errorObject.setField("success");
//                errorObject.setMessage("사용가능한 아이디입니다. 가입을 완료해 주세요");
//
//        } else if (checkedID >= 1) { //  중복 ID
//            errorObject.setMessage("이미 사용된 사용자 이름입니다. 다른 이름을 선택하세요.");
//            errorObject.setStatus(StatusEnum.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(errorObject, HttpStatus.OK);
//    }


