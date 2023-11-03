CREATE TABLE TERM_MST (
                          TERM_NO  Number(3)PRIMARY KEY,
                          TERM_TYPE VARCHAR2(99) NOT NULL,
                          TERM_YN VARCHAR2(3) DEFAULT 'Y' NOT NULL,
                          TERM_STARTDATE VARCHAR2(99) NOT NULL,
                          TERM_ENDDATE VARCHAR2(99) NOT NULL,
                          TERM_RGST_BY VARCHAR2(99) NOT NULL,
                          TERM_RGST_DATE DATE NOT NULL,
                          TERM_MDF_BY VARCHAR2(99),
                          TERM_MDF_DATE DATE
);

CREATE TABLE TERM_DTL (
                          TERM_NO NUMBER(3),
                          TERM_LANG VARCHAR2(9 CHAR) NOT NULL,
                          TERM_CNT CLOB NOT NULL
);


CREATE TABLE MEMBER (
                          MEMBER_NO NUMBER(3),
                          MEMBER_ID VARCHAR2(15) NOT NULL,
                          MEMBER_PASSWORD VARCHAR2(33) NOT NULL
);

CREATE SEQUENCE MEMBER_SEQ
START WITH 1  -- 시작 값, 1부터 시작하도록 설정
INCREMENT BY 1 -- 1씩 증가
NOCACHE; -- 시퀀스 캐싱 미사용