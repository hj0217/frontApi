CREATE TABLE TERM_MST (
                          TERM_NO  Number(3)PRIMARY KEY,
                          TERM_TYPE VARCHAR2(99) NOT NULL,
                          TERM_YN VARCHAR2(3) DEFAULT 'Y' NOT NULL,
                          TERM_STARTDATE DATE NOT NULL,
                          TERM_ENDDATE DATE NOT NULL,
                          TERM_RGST_BY VARCHAR2(99) NOT NULL,
                          TERM_RGST_DATE DATE NOT NULL,
                          TERM_MDF_BY VARCHAR2(99),
                          TERM_MDF_DATE DATE
);

CREATE TABLE TERM_DTL (
                          TERM_NO NUMBER(3) NOT NULL,
                          TERM_LANG VARCHAR2(9 CHAR) NOT NULL,
                          TERM_CNT CLOB NOT NULL
);