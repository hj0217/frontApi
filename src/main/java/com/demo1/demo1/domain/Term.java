package com.demo1.demo1.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Term {
    @Setter
    private int no;
    @Setter
    private String type;
    @Setter
    private String yn;
    @Setter
    private String startDate;
    @Setter
    private String endDate;
    @Setter
    private String rgstBy;
    @Setter
    private String rgstDate;
    @Setter
    private String mdfBy;
    @Setter
    private String mdfDate;
}