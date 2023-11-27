package com.demo1.demo1.member.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

public class ApiUtil {

    public static <T> ResponseEntity<T> sendRequest(String apiUrl, Object requestObject, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(requestObject, headers);
        return new RestTemplate().postForEntity(apiUrl, requestEntity, responseType);
    }
}
