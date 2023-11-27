package com.demo1.demo1.feign;

import com.demo1.demo1.board.domain.Term;
import com.demo1.demo1.board.domain.TermDtl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "terms", url = "http://localhost:8080/api/v1/terms")
public interface TermFeignClient {

    /*----------------------------------- 1. 상세페이지-------------------------------------*/
    @GetMapping("/getTerm")
    Term getTerm (@RequestParam("no") int no );

//    @GetMapping("/getListTermDtl")
//    List<TermDtl> getListTermDtl (@RequestParam("no") int no );

    /*----------------------------------- 2. 글 쓰기 -------------------------------------*/
    @PostMapping("/saveTerm")
    int saveTerm(@RequestBody Term term);

    /*----------------------------------- 글 수정 -------------------------------------*/
    @PostMapping("/updateTerm")
    int updateTerm(@RequestBody Term Term);

//    @PostMapping("/upadteListTermDtl")
//    int updateListTerm(@RequestBody Term Term);
}

