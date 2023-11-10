package com.demo1.demo1.board;

import com.demo1.demo1.board.service.TermService;
import com.demo1.demo1.board.domain.Term;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/v1/terms")
@RequiredArgsConstructor
public class TermController {

    private final TermService termService; // requiredArgsConstructor ->  선언된 필드가 꼭 필요하게 만들어줄 것! final & private으로 접근 제한까지!


    //1. 상세페이지 (GET)
    //http://localhost:9090/api/v1/terms?no=205
    @GetMapping("/detail")
    public String getTerm (@RequestParam int no, Model model) {
System.out.println("확인용1 getTerm :" + no);
        Term term = termService.getTerm(no);

        model.addAttribute("term", term);
        model.addAttribute("list", term.getTermDtlList());
        model.addAttribute("modify", null);

        return "/terms/detail";
    }

    //2. 글쓰기 (POST)
    //http://localhost:9090/api/v1/terms/save
    @PostMapping("/save")
    public String saveTerm (@RequestBody Term term, Model model) {
System.out.println("확인용1 saveTerm:" + term);
        int result = termService.saveTerm(term);

        if (result >= 2) {
            //정상 작업
        } else {
            //오류
        }
        return "redirect:/api/v1/main/";
    }

//    //3. 글수정(PUT)
//    //http://localhost:9090/api/v1/terms/update
//    @PutMapping("/update")
//    public String updateTerm (Term term, Model model) {
//        int result = termService.updateTerm(term);
//        return "redirect:/api/v1/main/";
//    }
}