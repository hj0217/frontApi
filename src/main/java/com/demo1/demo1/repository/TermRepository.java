package com.demo1.demo1.repository;

import com.demo1.demo1.domain.Term;

import java.util.List;

public interface TermRepository {

    List<Term> findAll();
    List<Term> search();
    Term findOne(int no);
}
