package com.demo1.demo1.main.service;


import com.demo1.demo1.main.domain.PageInfo;
import com.demo1.demo1.main.domain.Term;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.list;

//Web Client
/*
 * 액티브타입의 전송과 수신(mono, flux)
 *
 *      << 특징 >>
 * - 싱글 스레드 방식
 * - non-blocking 방식
 * - 비동기 접근 방식
 * - JSON, XML 쉽게 응답받음
 *
 * */

@Service
@RequiredArgsConstructor
public class MainService {


    private final WebClient webClient;


    /*-------------------------- Total Page 구하기-------------------------*/
//    public Mono<Integer> getTotalPage () {
//        return webClient.get()
//                .uri("/totalPage")
//                .retrieve()
//                .bodyToMono(Integer.class);
//                //ToDo
//                //.block() 쓰면 동기방식으로 업무 처리가 되는거임?!
//    }

    /*-------------------------메인페이지(BD데이터 List) ---------------------*/
    public List<Term> findAll(int boardLimit, int pageNum, String category, Term paramTerm) {
System.out.println("FService 1:" + boardLimit + "," + pageNum + "," + category );
    List<Term> list = new ArrayList<>();

        String encodedCategory = URLEncoder.encode(category, StandardCharsets.UTF_8);

        webClient.get()
        .uri(builder -> builder.path("/home")
                .queryParam("boardLimit",boardLimit)
                .queryParam("pageNum",pageNum)
                //.queryParam("category", encodedCategory)
                //.queryParam("startDate", paramTerm.getStartDate())
                //.queryParam("endDate",paramTerm.getEndDate())
                //.queryParam("type",paramTerm.getType())
                .build())// uriBuilder 끝
                .retrieve()
                .bodyToFlux(Term.class);
                //.subscribe(list::add);

        return list;
    }

//    /*-------------------------상세페이지-------------------------------------*/
//    public Term findOne(int no) {
//        return termMapper.findOne(no);
//    }
//
//    /*-----------------------상세 페이지 - 언어별 (ajax)-------------------------*/
//    //public TermDtl findConts(int no, String lang) {return jdbcTermRepository.findConts(no , lang);}
//    public List<TermDtl> findConts(int no) {
//        return termMapper.findConts(no);
//    }
//
//    /*------------------------------ 검색 ------------------------------------*/
//    public List<Term> search(Term term, String category) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put ("category", category);
//        map.put ("type" , term.getType());
//        map.put ("yn" , term.getYn());
//        if ("전시 시작일".equals(category)) {
//            map.put("startDate", term.getStartDate());
//            map.put("endDate", term.getEndDate());
//        } else if ("등록일".equals(category)) {
//            map.put("rgstDate", term.getRgstDate());
//        } else if ("수정일".equals(category)) {
//            map.put("mdfDate", term.getMdfDate());
//        }
//
//        return termMapper.search(map);
//    }
//
//    /*------------------------------ 등록  ------------------------------------*/
//  /*  @Transactional
//    public int register(Term term) {return jdbcTermRepository.register(term);}*/
//
//    //수동 commit 구현 매소드
////    public int register(Term term) { // method 1개에 sql문 1개...
////        int termNo = jdbcTermRepository.registerTerm(term);
////        return jdbcTermRepository.registerTermDtl(term, termNo);
////    }
//
//    @Transactional
//    public int register(Term term) { // method 1개에 sql문 1개...
//        int result = 0; // 저장 확인용
//        termMapper.registerTerm(term);
//        for (TermDtl termDtl : term.getTermDtlList()) {
//            termDtl.setNo(term.getNo()); // "term_no" 값을 설정
//            result += termMapper.registerTermDtl(termDtl);
//        }
//        return result;
//    }
//
//    /*------------------------------ 수정  ------------------------------------*/
//    @Transactional
//    public int update(Term term) {
//        int result  = 0;
//        result = termMapper.updateTerm(term);
//        for (TermDtl termDtl : term.getTermDtlList()) {
//            termDtl.setNo(term.getNo());
//            result += termMapper.updateTermDtl(termDtl);
//        }
//        return result;
//    }
//
//    /*------------------------------ 글 삭제  ------------------------------------*/
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
