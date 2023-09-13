package com.demo1.demo1.controller;

import com.demo1.demo1.domain.Term;
import com.demo1.demo1.domain.TermDtl;
import com.demo1.demo1.service.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller("/")
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;


    //메인페이지(BD데이터 List)
    @GetMapping("home")
    public String home(Model model) {
        List<Term> terms = termService.findAll();
        model.addAttribute("terms", terms);
        return "home";
    }


    @PostMapping("search")
    public String search(HttpServletRequest request, Term term, Model model) {
        //유형선택x 기본값 전체로 조회
        if (term.getType() == null || "전체".equals(term.getType())) {
            term.setType(null); // 계약서 타입을 비움
        } else {
            term.setType(term.getType());
        }

        //체크박스 null(unchekced) 시 각각 "N" , "Y"
        term.setYn(term.getYn()== null ? "N" : "Y");

        //날짜 (시작일 ~ 종료일) 정리해주기
        String openToEnd = request.getParameter("openToEnd");
        //기간 미설정 시 오늘 기준 1년 전까지 조회
        if (openToEnd == null || "".equals(openToEnd)) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            term.setEndDate(sdf.format(calendar.getTime())); // 오늘 날짜
            calendar.add(Calendar.YEAR, -1);
            term.setStartDate(sdf.format(calendar.getTime())); //오늘부터 365일 전 날짜
        } else {
            term.setStartDate(openToEnd.split("~")[0]);
            System.out.println(term.getStartDate());
            term.setEndDate(openToEnd.split("~")[0]);
            System.out.println(term.getEndDate());
        }

        List<Term> results = termService.Search(term);
        model.addAttribute("terms", results);
        return "home";
    }

    //상세보기 페이지
    @GetMapping("detail/{strNo}")
    public String detail(@PathVariable(value="strNo",required = false) String strNo,
                         @RequestParam(value="page", required = false) String page, Model model) {
        int no= parseInt(strNo);
        String lang = "ko";
            Term term = new Term();
            TermDtl termDtl = new TermDtl();

            term = termService.findOne(no);
            termDtl = termService.findConts(no, lang);

            model.addAttribute("term", term);
            String pageStatus = "oldPage";
            model.addAttribute("pageStatus", pageStatus);

            return "terms/detail";
    }



    // 언어구분 (ajax)
    @GetMapping("lang/{strNo}/{lang}")
    @ResponseBody
    public void lang (@PathVariable(value="strNo") String strNo,
                         @PathVariable(value="lang") String lang, HttpServletResponse response) throws IOException {

        int no= parseInt(strNo);
        TermDtl termDtl = termService.findConts(no,lang);
        PrintWriter out = response.getWriter();

        if (termDtl == null) {
            out.print("정보 없음");
        } else {
            out.print(termDtl);
        }
    }


    //등록 페이지
    @GetMapping("register")
    public String register (Model model){
        String pageStatus = "newPage";
        model.addAttribute("pageStatus", pageStatus);
        return "terms/detail";
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


        return "home/index";
    }
}
