package com.demo1.demo1.feign;

import com.demo1.demo1.board.domain.Term;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "main", url = "http://localhost:8080/api/v1/main")
public interface MainFeignClient {

    @GetMapping("/")
    Term home (@RequestParam("no") int no );

}

