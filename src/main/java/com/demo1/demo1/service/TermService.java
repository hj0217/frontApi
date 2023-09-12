package com.demo1.demo1.service;

import com.demo1.demo1.domain.Term;
import com.demo1.demo1.repository.JdbcTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {
    private final JdbcTermRepository jdbcTermRepository;

   /* @Autowired
    public TermService (JdbcTermRepository jdbcTermRepository) {
        this.jdbcTermRepository = jdbcTermRepository;
    }
    */
    public List<Term> findAll(){
        return jdbcTermRepository.findAll();
    }

    public Term findOne(int no) {
        return jdbcTermRepository.findOne(no);
    }

}
