package com.demo1.demo1.repository;

import com.demo1.demo1.domain.Term;
import com.demo1.demo1.domain.TermDtl;

import java.sql.SQLException;
import java.util.List;

public interface TermRepository {

    List<Term> findAll();
    List<Term> findAll(int pageNum);
    List<Term> search(Term term, String category);

    Term findOne(int no) throws SQLException;

    TermDtl findConts(int no, String lang);

    List<TermDtl> findConts(int no);

    int register(Term term);

    int listCount();
}
