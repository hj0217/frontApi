package com.demo1.demo1.repository;

import com.demo1.demo1.domain.PageInfo;
import com.demo1.demo1.domain.Term;
import com.demo1.demo1.domain.TermDtl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class JdbcTermRepository implements TermRepository {

    //public JdbcTermRepository(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

    private final DataSource dataSource;

    /* --------------------------------------------------게시글  수 카운트-------------------------------------------------------------------*/
    @Override
    public int listCount () {
        String sql = "SELECT COUNT(*) FROM TERM_MST";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            return rs.next() ? rs.getInt(1) : 0;

        }catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }
/*-----------------------------------------------------게시글 전체 가져오기 ---------------------------------------------------------------*/
    @Override
    public List<Term> findAll() {
        String sql = "SELECT * FROM TERM_MST";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {

            List<Term> terms = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    Term term = new Term();
                    term.setNo(rs.getInt("Term_no"));
                    term.setType(rs.getString("Term_type"));
                    term.setYn(rs.getString("Term_yn"));
                    term.setStartDate(rs.getString("Term_startDate"));
                    term.setEndDate(rs.getString("Term_endDate"));
                    term.setRgstBy(rs.getString("Term_rgst_by"));
                    term.setRgstDate(rs.getString("Term_rgst_date"));
                    term.setMdfBy(rs.getString("Term_mdf_by"));
                    term.setMdfDate(rs.getString("Term_mdf_date"));
                    terms.add(term);
                }
            }
            return terms;
        } catch (Exception e) {
            throw new IllegalStateException(e);

        }

    }

    @Override
    public List<Term> findAll(PageInfo pi) {
        int offset = (pi.getCurrentPage()-1) * pi.getBoardLimit();
        String sql = "SELECT * FROM TERM_MST ORDER BY term_rgst_Date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, offset);
            pst.setInt(2,pi.getBoardLimit());

            try (ResultSet rs = pst.executeQuery();) {
                List<Term> terms = new ArrayList<>();

                if (rs != null) {
                    while (rs.next()) {
                        Term term = new Term();
                        term.setNo(rs.getInt("Term_no"));
                        term.setType(rs.getString("Term_type"));
                        term.setYn(rs.getString("Term_yn"));
                        term.setStartDate(rs.getString("Term_startDate"));
                        term.setEndDate(rs.getString("Term_endDate"));
                        term.setRgstBy(rs.getString("Term_rgst_by"));
                        term.setRgstDate(rs.getString("Term_rgst_date"));
                        term.setMdfBy(rs.getString("Term_mdf_by"));
                        term.setMdfDate(rs.getString("Term_mdf_date"));
                        terms.add(term);
                    }
                }
                return terms;
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);

        }

    }

/*------------------------------------------상세 페이지-----------------------------------------*/
    @Override
    public Term findOne(int no) {
        String sql = "SELECT * FROM TERM_MST WHERE TERM_NO =?";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, no);

            try (ResultSet rs = pst.executeQuery();) {
                Term term = new Term();
                if (rs.next()) {
                    term.setNo(rs.getInt("Term_no"));
                    term.setType(rs.getString("Term_type"));
                    term.setYn(rs.getString("Term_yn"));
                    term.setStartDate(rs.getString("Term_startDate"));
                    term.setEndDate(rs.getString("Term_endDate"));
                    term.setRgstBy(rs.getString("Term_rgst_by"));
                    term.setRgstDate(rs.getString("Term_rgst_date"));
                    term.setMdfBy(rs.getString("Term_mdf_by"));
                    term.setMdfDate(rs.getString("Term_mdf_date"));

                }
                return term;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public TermDtl findConts(int no, String lang) {

        String sql = "SELECT * FROM TERM_DTL WHERE TERM_NO =? AND term_lang = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, no);
            pst.setString(2, lang);

            try (ResultSet rs = pst.executeQuery();) {
                TermDtl termDtl = new TermDtl();
                if (rs.next()) {
                    termDtl.setNo(rs.getInt("term_no"));
                    termDtl.setLang(rs.getString("term_lang"));
                    termDtl.setCnt(rs.getString("term_cnt"));
                }
                return termDtl;
            }
        } catch (Exception e) {

            throw new IllegalStateException(e);
        }
    }


    /*-------------------------------termDtl 컨텐츠 한번에 받아오기-------------------------------------*/
    @Override
    public List<TermDtl> findConts(int no) {

        String sql = "SELECT * FROM TERM_DTL WHERE TERM_NO =?";
        List<TermDtl> list = new ArrayList<TermDtl>();

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, no);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    TermDtl termDtl = new TermDtl();
                    termDtl.setNo(rs.getInt("term_no"));
                    termDtl.setLang(rs.getString("term_lang"));
                    termDtl.setCnt(rs.getString("term_cnt"));
                    list.add(termDtl);
                }
                return list;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    /*--------------------------------------신규 등록 (Ver. manual-commit) ----------------------------------------------*/
    @Override
    public int register(Term term) {

        Connection conn = null;
        int termNo = 0;

        try {
           conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234");

            //트랜잭션 시작 // 자동 커밋 기능 off
            conn.setAutoCommit(false);

            //1. Term_mst insert
            String insertTermSql = "INSERT INTO TERM_MST (term_no, term_type, term_yn, term_startDate, term_endDate, term_rgst_By, term_rgst_Date) VALUES (term_seq.nextval, ?, ?, ?, ?, ?, SYSDATE)";

                PreparedStatement pst1 = conn.prepareStatement(insertTermSql, new String[]{"term_no"});
                pst1.setString(1, term.getType());
                pst1.setString(2, term.getYn());
                pst1.setString(3, term.getStartDate());
                pst1.setString(4, term.getEndDate());
                pst1.setString(5, "이형주");

                int rs = pst1.executeUpdate();

                if (rs > 0 ) {
                    ResultSet key = pst1.getGeneratedKeys();
                    if (key.next()) {
                        termNo = key.getInt(1);
                    } else {
                        throw new SQLException("termNo값 얻기 실패");
                    }
                } else {
                    throw new SQLException("Term data 등록실패");
                }
                pst1.close();


            //2. Term_dtl insert
            String insertTermDtlSql = "INSERT INTO TERM_DTL (TERM_no, TERM_LANG, TERM_CNT) VALUES (?, ?, ?)";
                PreparedStatement pst2 = conn.prepareStatement(insertTermDtlSql);

                for (TermDtl termDtl : term.getTermDtlList()) {
                    pst2.setInt(1, termNo);
                    pst2.setString(2, termDtl.getLang());
                    pst2.setString(3, termDtl.getCnt());
                    pst2.executeUpdate();
                }
                pst2.close();

            //수동 커밋
            conn.commit();
            System.out.println("업로드 성공");

        } catch (Exception e) {
            try {
                System.out.println("업로드 실패 - rollback");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("rollback - rollback 실패");
                throw new RuntimeException(ex);
            }
       //트랜잭션 끝
        }  finally {
            if(conn != null) {
                try {
                    // 자동 커밋 기능 on
                    conn.setAutoCommit(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return termNo ;
    }

    /*--------------------------------------신규 등록 (Ver. auto-commit) ----------------------------------------------*/
//    @Override
//    public int register(Term term) {
//        String insertTermSql = "INSERT INTO TERM_MST (term_no, term_type, term_yn, term_startDate, term_endDate, term_rgst_By, term_rgst_Date) VALUES (term_seq.nextval, ?, ?, ?, ?, ?, SYSDATE)";
//        String insertTermDtlSql = "INSERT INTO TERM_DTL (TERM_no, TERM_LANG, TERM_CNT) VALUES (?, ?, ?)";
//
//        int termNo = 0;
//
//        try (Connection conn = dataSource.getConnection()) {
//            try (PreparedStatement pst = conn.prepareStatement(insertTermSql, new String[]{"term_no"})) {
//                pst.setString(1, term.getType());
//                pst.setString(2, term.getYn());
//                pst.setString(3, term.getStartDate());
//                pst.setString(4, term.getEndDate());
//                pst.setString(5, "이형주");
//
//                int rs = pst.executeUpdate();
//                if (rs > 0) {
//                    ResultSet key = pst.getGeneratedKeys();
//                    if (key.next()) {
//                        termNo = key.getInt(1);
//                    } else {
//                        throw new SQLException("termNo값 얻기 실패");
//                    }
//                } else {
//                    throw new SQLException("term insert  실패");
//                }
//            }
//
//            try (PreparedStatement pst = conn.prepareStatement(insertTermDtlSql)) {
//                for (TermDtl termDtl : term.getTermDtlList()) {
//                    pst.setInt(1, termNo);
//                    pst.setString(2, termDtl.getLang());
//                    pst.setString(3, termDtl.getCnt());
//                    pst.executeUpdate();
//                }
//            }
//
//            return termNo;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw new RuntimeException(ex);
//        }
//    }

    /*--------------------------------------신규 등록 (term / termDTL이 각각 commit됨.)----------------------------------------------*/
   /* @Override
    public register(Term term) {
        String sql = "INSERT INTO TERM_MST (term_no, term_type, term_yn, term_startDate, term_endDate, term_rgst_By, term_rgst_Date) VALUES (term_seq.nextval, ?, ?, ?, ?, ?, SYSDATE)";

        int termNo;

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql , new String[]{"term_no"})) {
            pst.setString(1, term.getType());
            pst.setString(2, term.getYn());
            pst.setString(3, term.getStartDate());
            pst.setString(4, term.getEndDate());

            pst.setString(3, "2021-01-01");
            pst.setString(4, "2021-01-02");

            pst.setString(5, "김철수");

            int rs  = pst.executeUpdate();
                if (rs > 0 ) {
                    ResultSet key = pst.getGeneratedKeys();
                    if (key.next()) {
                        termNo = key.getInt(1);
                    } else {
                        throw new SQLException("termNo값 얻기 실패");
                    }
                } else {
                    throw new SQLException("term insert  실패");
                }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        String sql_ = "INSERT INTO TERM_DTL (TERM_no, TERM_LANG, TERM_CNT) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql_);) {

            for (TermDtl termDtl : term.getTermDtlList()) {
                pst.setInt(1, termNo); // term_no 설정
                pst.setString(2, termDtl.getLang()); // TERM_LANG 설정
                pst.setString(3, termDtl.getCnt()); // TERM_CNT 설정
                pst.executeUpdate();
            }

        } catch (SQLException e) {
            e.getStackTrace();
            System.out.println("List로 넣는 부분");
        }
    }*/



/*-----------------------------------------검색------------------------------------------------*/
    @Override
    public List<Term> search(Term term, String categoty) {

        List<Term> terms = new ArrayList<>();
        String sql = "";

        if (term.getType().equals("이용약관") || term.getType().equals("개인정보취급방침") || term.getType().equals("회원가입 동의") || term.getType().equals("주문동의")) { //검색 시 타입 미설정 시 "전체"로 검색

            switch (categoty) {
                case "전시 시작일" :
                    sql = "select * from TERM_MST WHERE TERM_TYPE = ? AND TERM_YN = ? AND TO_DATE(TERM_STARTDATE, 'YYYY-MM-DD') >= TO_DATE(?, 'YYYY-MM-DD')";
                    //sql = "select * from TERM_MST WHERE TERM_TYPE = ? AND TERM_YN = ?";
                    break;
                case "등록일" :
                    sql = "select * from TERM_MST WHERE TERM_TYPE = ? AND TERM_YN = ? AND TO_DATE(TERM_RGST_DATE, 'YYYY-MM-DD') >= TO_DATE(?, 'YYYY-MM-DD')";
                    break;
                case "수정일" :
                    sql = "select * from TERM_MST WHERE TERM_TYPE = ? AND TERM_YN = ? AND TO_DATE(TERM_MDF_DATE, 'YYYY-MM-DD') >= TO_DATE(?, 'YYYY-MM-DD')";
                    break;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {

                pst.setString(1, term.getType());
                pst.setString(2, term.getYn());
                pst.setString(3, term.getStartDate());


                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Term termrs = new Term();
                        termrs.setNo(rs.getInt("Term_no"));
                        termrs.setType(rs.getString("Term_type"));
                        termrs.setYn(rs.getString("Term_yn"));
                        termrs.setStartDate(rs.getString("Term_startDate"));
                        termrs.setEndDate(rs.getString("Term_endDate"));
                        termrs.setRgstBy(rs.getString("Term_rgst_by"));
                        termrs.setRgstDate(rs.getString("Term_rgst_date"));
                        termrs.setMdfBy(rs.getString("Term_mdf_by"));
                        termrs.setMdfDate(rs.getString("Term_mdf_date"));
                        terms.add(termrs);
                    }
                }

                return terms;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }

        } else { // 전체 검색

            switch (categoty) {
                case "전시 시작일" :
                    sql = "select * from TERM_MST WHERE TERM_YN = ? AND TO_DATE(TERM_STARTDATE, 'YYYY-MM-DD') >= TO_DATE(?, 'YYYY-MM-DD')";
                    break;
                case "등록일" :
                    sql = "select * from TERM_MST WHERE TERM_YN = ? AND TO_DATE(TERM_RGST_DATE, 'YYYY-MM-DD') >= TO_DATE(?, 'YYYY-MM-DD')";
                    break;
                case "수정일" :
                    sql = "select * from TERM_MST WHERE TERM_YN = ? AND TO_DATE(TERM_MDF_DATE, 'YYYY-MM-DD') >= TO_DATE(?, 'YYYY-MM-DD')";
                    break;
            }


            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234"); PreparedStatement pst = conn.prepareStatement(sql);) {

                pst.setString(1, term.getYn());
                pst.setString(2, term.getStartDate());

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Term termrs = new Term();
                        termrs.setNo(rs.getInt("Term_no"));
                        termrs.setType(rs.getString("Term_type"));
                        termrs.setYn(rs.getString("Term_yn"));
                        termrs.setStartDate(rs.getString("Term_startDate"));
                        termrs.setEndDate(rs.getString("Term_endDate"));
                        termrs.setRgstBy(rs.getString("Term_rgst_by"));
                        termrs.setRgstDate(rs.getString("Term_rgst_date"));
                        termrs.setMdfBy(rs.getString("Term_mdf_by"));
                        termrs.setMdfDate(rs.getString("Term_mdf_date"));
                        terms.add(termrs);
                    }
                }
                return terms;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

    }

} //
