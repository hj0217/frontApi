package com.demo1.demo1.repository;

import com.demo1.demo1.vo.Term;
import com.demo1.demo1.vo.TermDtl;
import com.demo1.demo1.service.TermService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
public class JdbcTermRepositoryTest {

    @Autowired JdbcTermRepository repository;
    @Autowired TermService termService;


    @Test
    public void 글조회() throws Exception {
        //Given

        //When

        //Then
        Term findTerm = repository.findOne(158);
        System.out.println(findTerm);
    }



    @Test
    public void 글쓰기() throws Exception {
        //Given
        Term term = new Term();
        term.setType("Test 용어 타입");
        term.setYn("Y");
        term.setStartDate("2023-01-01");
        term.setEndDate("2023-12-31");

        List<TermDtl> list = new ArrayList<>();

        TermDtl termDtl1 = new TermDtl();
        termDtl1.setLang("ko");
        termDtl1.setCnt("dummyData1");

        TermDtl termDtl2 = new TermDtl();
        termDtl2.setLang("en");
        termDtl2.setCnt("dummyData2");
        list.add(termDtl1);
        list.add(termDtl2);

        term.setTermDtlList(list);

        //When
        int termNo = repository.registerTerm(term);
        System.out.println(termNo);
        repository.registerTermDtl(term, termNo);
        System.out.println(termNo);

        //Then: 등록 결과 확인
        assertNotEquals(0, termNo);
    }

}
