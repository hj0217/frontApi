package com.demo1.demo1.main;

import com.demo1.demo1.main.domain.PageInfo;
import com.demo1.demo1.main.domain.Term;
import com.demo1.demo1.main.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class mainController {

    private final MainService mainService;

    @GetMapping (value= "")
    public List<Term> home (@RequestParam(value = "boardLimit", required = false, defaultValue = "30") int boardLimit,
                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "category", required = false, defaultValue = "전체") String category,
                        Term termParam, Model model) {


        //전체

            System.out.println("FController 1:" + termParam);

            // server api 요청
        List<Term> list = mainService.findAll(boardLimit, pageNum, category, termParam);


        return list;
    }
}
