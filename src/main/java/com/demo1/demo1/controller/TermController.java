package com.demo1.demo1.controller;

import com.demo1.demo1.domain.PageInfo;
import com.demo1.demo1.domain.Term;
import com.demo1.demo1.domain.TermDtl;
import com.demo1.demo1.service.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/terms")
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;


    /*--------------------------------메인페이지(BD데이터 List)----------------------------------------*/
//    @RequestMapping("/")
//    public String home(Model model) {
//        List<Term> terms = termService.findAll();
//
//        model.addAttribute("terms", terms);
//        return "home";
//    }


//페이징 처리완료
    @GetMapping("/")
    public String home(@RequestParam(value = "boardLimit", required=false, defaultValue = "30") int boardLimit,
                       @RequestParam(value = "pageNum", required=false, defaultValue = "1") int pageNum,
                       Model model) {

        // 검색 조건 확인 (검색결과 몇개 출력 / 현재 페이지 번호 / 검색 조건 (Term)

        ////전체 게시글 구하기
        int listCount = termService.listCount();
        int pageLimit = 5;	// 보여질 페이지 수(하단 페이지 번호)

        PageInfo pageInfo = Pagination.getPageInfo(listCount, pageNum, pageLimit, boardLimit);

        List<Term> terms = termService.findAll(pageInfo);
        model.addAttribute("terms", terms);
        model.addAttribute("pi", pageInfo);

        return "home";
    }



    /*-----------------------------------------검색 search------------------------------------------------*/
    @PostMapping("/search")
    public String search(@RequestParam(value="type", required = false) String searchType,
                         @RequestParam(value="pageNum", required = false, defaultValue = "1") int pageNum,
                         Term term, Model model) {

        System.out.println("search  들어온 term 내용 :" + term);

        List<Term> terms = termService.search(term, searchType);
        int listCount = terms.size();
        int boardLimit = 30;
        int pageLimit = 5;	// 보여질 페이지 수(하단 페이지 번호)
        PageInfo pageInfo = Pagination.getPageInfo(listCount, pageNum, pageLimit, boardLimit);


        model.addAttribute("terms", terms);
        model.addAttribute ("searchType", searchType);
        model.addAttribute("pi", pageInfo);

        return "home";
    }


    /*------------------------------등록 페이지 & 상세 페이지--------------------------------------*/
    @GetMapping(value = {"/detail/" , "/detail/{strNo}"})
    public String detail(@PathVariable("strNo") Optional<String> strNo,
                         Model model) {

        if(strNo.isPresent()) { // 상세페이지
            int no = Integer.parseInt(strNo.get());
//            List<TermDtl> list = new ArrayList<>();
    /*--------------9.20 수정 전 코드----------------------*/
//            list.add(termService.findConts(no, "ko"));
//            list.add(termService.findConts(no, "en"));
//            list.add(termService.findConts(no, "cn"));
//            list.add(termService.findConts(no, "jp"));
    /*-----------------수정 후 코드-----------------------*/

            model.addAttribute("term", termService.findOne(no));
            model.addAttribute("list" ,termService.findConts(no));

            return "terms/detail";

        } else { //등록 페이지
            model.addAttribute("term", null);
            model.addAttribute("list" , null);
            return "/terms/detail";
        }
    }

    /*------------------------------상세 페이지 - 언어별 (ajax)--------------------------------------*/
//    @PostMapping(value = "/lang")
//    @ResponseBody
//    public TermDtl langCnt (@RequestBody TermDtl termDtlP) {
//
//        return termService.findConts(termDtlP.getNo(),termDtlP.getLang());
//    }



    /*--------------------------------------신규 등록----------------------------------------------*/
//    @PostMapping("/register")
//    public String register(Term term) {
//
//        termService.register(term);
//        return "redirect:/terms/";
//    }

    /*--------------------------------------신규 등록 (ajax-form-serialize)----------------------------------------------*/
    @PostMapping(value = "/register")
    @ResponseBody
    public int register (@RequestBody Term term) {
        System.out.println("Controller body 값 확인용:" + term);
        return termService.register(term);
    }
}
