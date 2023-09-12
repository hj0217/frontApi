package com.demo1.demo1.controller;

import com.demo1.demo1.domain.Term;
import com.demo1.demo1.service.TermService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller("/")
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;


   /* @Autowired //RequiredArgsConstructor는 final이 붙은 필드의 생성자 초기화를 해줌! (Lombok기능)
    public TermController(TermService termService) {
        this.termService  = termService;
    } */


    //메인페이지(BD데이터 List)
    @GetMapping("home")
    public String home(Model model) {
        List<Term> terms = termService.findAll();
        model.addAttribute("terms", terms);
        return "home";
    }

    //상세보기 페이지
    @GetMapping("detail/{strNo}")
    public String detail(@PathVariable(value="strNo",required = false) String strNo, @RequestParam(value="page", required = false) String page, Model model) {
        int no= parseInt(strNo);

        // strno  > 0 ( 글보기 클릭)
        if (no > 0) {
            System.out.println(strNo);
            Term term = new Term();
            term = termService.findOne(no);
            model.addAttribute("term", term);
            return "terms/detail";

        //strNo
        } else {
            System.out.println("빈값이어야");
            return "terms/detail";
        }

    }


    //신규 등록
    @PostMapping("registerForm")
    public String registerForm(HttpServletRequest request, HttpServletResponse response) {
        /*날짜 데이터 가공*/
        String openToEnd = request.getParameter("openToEnd");
        String open = openToEnd.split(" ~ ")[0];
        String end = openToEnd.split(" ~ ")[1];


        /*언어 Checkbox 정리*/
        String ko = request.getParameter("ko") == null ? null : request.getParameter("ko");
        String en = request.getParameter("en") == null ? null : request.getParameter("en");
        String cn = request.getParameter("cn") == null ? null : request.getParameter("cn");
        String jp = request.getParameter("jp") == null ? null : request.getParameter("jp");


        return "home";
    }
}
