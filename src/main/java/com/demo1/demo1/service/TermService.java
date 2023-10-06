package com.demo1.demo1.service;

import com.demo1.demo1.domain.Term;
import com.demo1.demo1.domain.TermDtl;
import com.demo1.demo1.repository.JdbcTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo1.demo1.domain.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {

    //private final JdbcTermRepository jdbcTermRepository;
    private final JdbcTermRepository jdbcTermRepository;

//   @Autowired
//    public TermService (JpaTermRepository jdbcTermRepository) {
//        this.jdbcTermRepository = jdbcTermRepository;
//    }


    /*-------------------------- Total Page 구하기-------------------------*/
    public int listCount () {
        return jdbcTermRepository.listCount();
    }


    /*-------------------------메인페이지(BD데이터 List) ---------------------*/
    public List<Term> findAll(){return jdbcTermRepository.findAll();}
    public List<Term> findAll(PageInfo pi){return jdbcTermRepository.findAll(pi);}


    /*-------------------------상세페이지-------------------------------------*/
    public Term findOne(int no) {
        return jdbcTermRepository.findOne(no);
    }

    /*-----------------------상세 페이지 - 언어별 (ajax)-------------------------*/
    public TermDtl findConts(int no, String lang) {return jdbcTermRepository.findConts(no , lang);}
    public List<TermDtl> findConts(int no) {
        return jdbcTermRepository.findConts(no);
    }

    /*------------------------------ 검색 ------------------------------------*/
    public List<Term> search(Term term, String category) {return jdbcTermRepository.search(term, category);}

    /*------------------------------ 등록  ------------------------------------*/
    @Transactional
    public int register(Term term) {return jdbcTermRepository.register(term);}

}
