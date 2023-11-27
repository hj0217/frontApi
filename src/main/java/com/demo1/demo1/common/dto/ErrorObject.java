package com.demo1.demo1.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ErrorObject {
    private String field;
    private String message;
}
