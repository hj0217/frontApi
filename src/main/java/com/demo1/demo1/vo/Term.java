package com.demo1.demo1.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;


@Getter
@Setter
@ToString
public class Term {

    private int no;

    private String type;
    private String yn;
    private String startDate;
    private String endDate;
    private String rgstBy;
    private String rgstDate;
    private String mdfBy;
    private String mdfDate;
    private List<TermDtl> termDtlList;
}

