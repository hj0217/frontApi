package com.demo1.demo1.repository;

import com.demo1.demo1.domain.Term;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
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

        try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst =conn.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            List<Term> terms = new ArrayList<>();

            if (rs != null) {
                while(rs.next()) {
                    Term term = new Term();
                    setTerm(term,rs);
                    terms.add(term);
                }
            }
            return terms;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public Term findOne(int no) {
        String sql = "select * from TERM_MST where TERM_NO =?";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
                pst.setInt(1, no);

                try (ResultSet rs = pst.executeQuery();) {
                    Term term = new Term();
                    if (rs.next()){
                        setTerm(term,rs);
                    }
                    return term;
                }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Term> search() {
        return null;
    }

    //중복코드
    private void setTerm(Term term, ResultSet rs) throws SQLException {
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

}
