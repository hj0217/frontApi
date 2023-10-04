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
    @RequestMapping("/")
    public String home(@RequestParam(value = "boardLimit", required=false, defaultValue = "30") String boardLimitStr,
                       @RequestParam(value = "pageNum", required=false, defaultValue = "1") String pageNumStr,
                       Model model) {

        // 검색 조건 확인 (검색결과 몇개 출력 / 현재 페이지 번호 / 검색 조건 (Term)
        int boardLimit = Integer.parseInt(boardLimitStr);
        int PageNum = Integer.parseInt(pageNumStr);


        ////전체 게시글 구하기
        int listCount = termService.listCount();
        int pageLimit = 5;	// 보여질 페이지 수(하단 페이지 번호)

        PageInfo pi = Pagination.getPageInfo(listCount, PageNum, pageLimit, boardLimit);

        List<Term> terms = termService.findAll(pi);
        model.addAttribute("terms", terms);
        model.addAttribute("pi", pi);

        return "home";
    }



    /*-----------------------------------------검색------------------------------------------------*/
    @PostMapping("/search")
    public String search(@RequestParam(value="searchType") String searchType,
                         Term term, Model model) {

        List<Term> terms = termService.search(term, searchType);
        System.out.println(terms.toString());

        model.addAttribute("terms", terms);
        return "home";
    }


    /*------------------------------등록 페이지 & 상세 페이지--------------------------------------*/
    @RequestMapping(value = {"/detail/" , "/detail/{strNo}"})
    public String detail(@RequestParam(value="page", required = false) String page,
                         @PathVariable("strNo") Optional<String> strNo,
                         Model model) {

        if(strNo.isPresent()) { // 상세페이지
            int no = Integer.parseInt(strNo.get());
            List<TermDtl> list = new ArrayList<>();
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
//    @Transactional
//    @PostMapping("/register")
//    public String register(Term term) {
//
//        termService.register(term);
//        return "redirect:/terms/";
//    }

    /*--------------------------------------신규 등록 (ajax-form-serialize)----------------------------------------------*/
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public int register (@RequestBody Term term) {
    //public int register (@RequestParam (value= "type") String type) {
        System.out.println(term.getNo());
        System.out.println(term.getType());
        System.out.println(term.getYn());
        System.out.println(term.getStartDate());
        System.out.println(term.getEndDate());
        System.out.println(term.getTermDtlList().get(0).getCnt());

        //System.out.println(type);
        //return 1;
        return termService.register(term);
    }
}
