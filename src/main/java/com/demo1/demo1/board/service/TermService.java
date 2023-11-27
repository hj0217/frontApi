package com.demo1.demo1.board.service;

import com.demo1.demo1.board.domain.Term;
import com.demo1.demo1.board.domain.TermDtl;
import com.demo1.demo1.feign.TermFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {

    private final TermFeignClient termFeignClient;


    /*----------------------------------- 1. 상세페이지-------------------------------------*/
    public Term getTerm(int no) {
System.out.println("확인용2 getTerm:" + no);
        Term term = termFeignClient.getTerm(no);
        //List<TermDtl> termDtl = termFeignClient.getListTermDtl(no);
        //term.setTermDtlList(termDtl);
        return term;
    }

    /*------------------------------ 2. 글 쓰기   ------------------------------------*/
    public int saveTerm(Term term) {
        System.out.println("확인용2 saveTerm:" + term);
        return termFeignClient.saveTerm(term);
    }

   /*------------------------------ 3. 글 수정  ------------------------------------*/

    public int updateTerm(Term term) {
        int result  = 0;
        result = termFeignClient.updateTerm(term);
//        for (TermDtl termDtl : term.getTermDtlList()) {
//            termDtl.setNo(term.getNo());
//            result += termFeignClient.updateListTerm(term);
//        }
        return result;
    }
//
//    /*------------------------------ 4. 글 삭제  ------------------------------------*/
//    public int delete(int no) {
//        termMapper.delete(no);
//        return 1;
//    }
//
//
//
//
//    /*------------------------------  회원가입  ------------------------------------*/
//    public int memberRegister(Member member) {
//        member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
//        return termMapper.memberRegister(member);
//    }
//
//
//    /*------------------------------  로그인  ------------------------------------*/
//    public Member login(Member member) throws LoginException {
//
//            boolean isPasswordMatch = bCryptPasswordEncoder.matches( member.getPwd(), termMapper.getPassword(member.getId()));
//
//System.out.println(isPasswordMatch);
//
//                if (isPasswordMatch) {
//System.out.println("확인용 서비스 성공: " + termMapper.memberLogin(member));
//System.out.println(bCryptPasswordEncoder.encode(member.getPwd()));
//
//                    member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
//                    return termMapper.memberLogin(member);
//
//                } else {
//                    System.out.println("확인용 서비스 실패");
//                    throw new LoginException("Password does not match");
//                }
//    }
//
//    /*------------------------------   ID 중복체크  ------------------------------------*/
//    public int idCheck(String id) {
//        return termMapper.idCheck(id);
//    }
}
