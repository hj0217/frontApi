package com.demo1.demo1.main.domain;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


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

}

