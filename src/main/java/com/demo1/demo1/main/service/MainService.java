package com.demo1.demo1.main.service;

import com.demo1.demo1.feign.MainFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MainService {

    private final MainFeignClient mainFeignClient;






//    /*-------------------------- Total Page 구하기-------------------------*/
//    public int listCount () {
//        return termMapper.listCount();
//    }
//
//
//    /*-------------------------메인페이지(BD데이터 List) ---------------------*/
//    //public List<Term> findAll(){return jdbcTermRepository.findAll();}
//    public List<Term> findAll(PageInfo pi){return termMapper.findAll(pi);}
//
//
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
