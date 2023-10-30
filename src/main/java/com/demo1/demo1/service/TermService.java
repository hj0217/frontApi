package com.demo1.demo1.service;

import com.demo1.demo1.mapper.TermMapper;
import com.demo1.demo1.vo.Term;
import com.demo1.demo1.vo.TermDtl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo1.demo1.vo.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {

    //private final JdbcTermRepository jdbcTermRepository;
    //private final JdbcTermRepository jdbcTermRepository;
      private final TermMapper termMapper;
//   @Autowired
//    public TermService (JpaTermRepository jdbcTermRepository) {
//        this.jdbcTermRepository = jdbcTermRepository;
//    }


    /*-------------------------- Total Page 구하기-------------------------*/
    public int listCount () {
        return termMapper.listCount();
    }


    /*-------------------------메인페이지(BD데이터 List) ---------------------*/
    //public List<Term> findAll(){return jdbcTermRepository.findAll();}
    public List<Term> findAll(PageInfo pi){return termMapper.findAll(pi);}


    /*-------------------------상세페이지-------------------------------------*/
    public Term findOne(int no) {
        return termMapper.findOne(no);
    }

    /*-----------------------상세 페이지 - 언어별 (ajax)-------------------------*/
    //public TermDtl findConts(int no, String lang) {return jdbcTermRepository.findConts(no , lang);}
    public List<TermDtl> findConts(int no) {
        return termMapper.findConts(no);
    }

    /*------------------------------ 검색 ------------------------------------*/
    public List<Term> search(Term term, String category) { return termMapper.search(term, category); }

    /*------------------------------ 등록  ------------------------------------*/
  /*  @Transactional
    public int register(Term term) {return jdbcTermRepository.register(term);}*/

    //수동 commit 구현 매소드
//    public int register(Term term) { // method 1개에 sql문 1개...
//        int termNo = jdbcTermRepository.registerTerm(term);
//        return jdbcTermRepository.registerTermDtl(term, termNo);
//    }
    @Transactional
    public int register(Term term) { // method 1개에 sql문 1개...
        int result = 0; // 저장 확인용
        termMapper.registerTerm(term);
        for (TermDtl termDtl : term.getTermDtlList()) {
            termDtl.setNo(term.getNo()); // "term_no" 값을 설정
            result += termMapper.registerTermDtl(termDtl);
        }
        return result;
    }

    /*------------------------------ 수정  ------------------------------------*/
    @Transactional
    public int update(Term term) {
        int result  = 0;
        result = termMapper.updateTerm(term);
        for (TermDtl termDtl : term.getTermDtlList()) {
            termDtl.setNo(term.getNo());
            result += termMapper.updateTermDtl(termDtl);
        }
        return result;
    }
}
