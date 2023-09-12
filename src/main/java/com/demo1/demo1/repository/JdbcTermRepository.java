package com.demo1.demo1.repository;

import com.demo1.demo1.domain.Term;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JdbcTermRepository implements TermRepository{

    private final DataSource dataSource;

    //public JdbcTermRepository(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

    @Override
    public List<Term> findAll() {
        String sql = "select * from term_mst";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Term> terms = new ArrayList<>();

            if (rs != null) {
                while(rs.next()) {
                    Term term = new Term();
                    term.setNo(rs.getInt("Term_no"));
                    term.setType(rs.getString("Term_type"));
                    term.setYn(rs.getString("Term_YN"));
                    term.setStartDate(rs.getString("Term_startDate"));
                    term.setEndDate(rs.getString("Term_Enddate"));
                    term.setRgstBy(rs.getString("Term_rgst_by"));
                    term.setRgstDate(rs.getString("Term_rgst_Date"));
                    term.setMdfBy(rs.getString("Term_MDF_By"));
                    term.setMdfDate(rs.getString("Term_MDF_DATE"));
                    terms.add(term);
                }
            }
            return terms;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {

        }

    }

    @Override
    public Term findOne(int no) {
        String sql = "select * from TERM_MST where TERM_NO = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            Term term = new Term();

            if ((rs.next())){

                    term.setNo(rs.getInt("Term_no"));
                    term.setType(rs.getString("Term_type"));
                    term.setYn(rs.getString("Term_YN"));
                    term.setStartDate(rs.getString("Term_startDate"));
                    term.setEndDate(rs.getString("Term_Enddate"));
                    term.setRgstBy(rs.getString("Term_rgst_by"));
                    term.setRgstDate(rs.getString("Term_rgst_Date"));
                    term.setMdfBy(rs.getString("Term_MDF_By"));
                    term.setMdfDate(rs.getString("Term_MDF_DATE"));

            }
            return term;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {

        }

    }

    @Override
    public List<Term> search() {
        return null;
    }


}


