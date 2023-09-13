package com.demo1.demo1.repository;

import com.demo1.demo1.domain.Term;
import com.demo1.demo1.domain.TermDtl;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JdbcTermRepository implements TermRepository {

    private final DataSource dataSource;

    //public JdbcTermRepository(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

    @Override
    public List<Term> findAll() {
        String sql = "select * from term_mst";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            List<Term> terms = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    Term term = new Term();
                    setTerm(term, rs);
                    terms.add(term);
                }
            }
            return terms;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    //중복코드제거
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


    @Override
    public Term findOne(int no) {
        String sql = "select * from TERM_MST where TERM_NO =?";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, no);

            try (ResultSet rs = pst.executeQuery();) {
                Term term = new Term();
                if (rs.next()) {
                    setTerm(term, rs);
                }
                return term;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public TermDtl findConts(int no, String lang) {

        String sql = "select* from TERM_DTL WHERE TERM_NO =? and term_lang = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, no);
            pst.setString(2, lang);

            try (ResultSet rs = pst.executeQuery();) {
                TermDtl termDtl = new TermDtl();
                if (rs.next()) {
                    termDtl.setNo(rs.getInt("term_no"));
                    termDtl.setLang(rs.getString("term_lang"));
                    termDtl.setCnt(rs.getString("term_ctn"));
                }
                return termDtl;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }




    @Override
    public List<Term> search(Term term) {

        List<Term> terms = new ArrayList<>();
        //약관유형 기준으로 나눠짐 => "전체" 또는 "특정유형"
        if (term.getType() ==null) {
            //String sql = "select * from TERM_MST WHERE TERM_YN = ? and TERM_STARTDATE = ? and TERM_ENDDATE = ?";
            String sql = "select * from TERM_MST WHERE TERM_YN = ?";

            System.out.println("Type null case");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
                pst.setString(1, term.getYn());
                //pst.setString(2, term.getStartDate());
               // pst.setString(3, term.getEndDate());

                return getTerms(terms, pst);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }

        } else {
            //String sql = "select * from TERM_MST WHERE TERM_TYPE = ? and TERM_YN = ? and TERM_STARTDATE = ? and TERM_ENDDATE = ?";
            String sql = "select * from TERM_MST WHERE TERM_TYPE = ? and TERM_YN = ?";

            System.out.println("Type 특정조회 case:" + term.getType());

            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
                pst.setString(1, term.getType());
                pst.setString(2, term.getYn());
                //pst.setString(3, term.getStartDate());
                //pst.setString(4, term.getEndDate());

                return getTerms(terms, pst);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }

        }

    }

    //중복코드제거
    private List<Term> getTerms(List<Term> terms, PreparedStatement pst) {
        try (ResultSet rs = pst.executeQuery();) {
            Term result = new Term();
            if (rs != null) {
                while (rs.next()) {
                    setTerm(result, rs);
                    terms.add(result);
                }
            }
            return terms;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}


