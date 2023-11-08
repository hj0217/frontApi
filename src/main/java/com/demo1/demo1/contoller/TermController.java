package com.demo1.demo1.contoller;

import com.demo1.demo1.vo.PageInfo;
import com.demo1.demo1.service.TermService;
import com.demo1.demo1.vo.Term;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller @RequestMapping("/terms")
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



    @GetMapping("/")
    public String home(@RequestParam(value = "boardLimit", required = false, defaultValue = "30") int boardLimit,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                       Model model) {


        ////전체 게시글 구하기
        int listCount = termService.listCount();
        int pageLimit = 5;    // 보여질 페이지 수(하단 페이지 번호)

        PageInfo pageInfo = PaginationController.getPageInfo(listCount, pageNum, pageLimit, boardLimit);

        List<Term> terms = termService.findAll(pageInfo);
        model.addAttribute("terms", terms);
        model.addAttribute("pi", pageInfo);
        //Member member = new Member();

        return "home";
    }


    /*-----------------------------------------검색 search------------------------------------------------*/
    @PostMapping("/search")
    public String search(@RequestParam(value = "type", required = false) String searchType,
                         @RequestParam(value = "category") String category,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         Term term, Model model) {

System.out.println(term);
System.out.println(searchType);
System.out.println(category);
System.out.println(pageNum);

        List<Term> terms = termService.search(term, category);
 System.out.println("확인용1!!!!!!!!");
        int listCount = terms.size();
        int boardLimit = 100;
        int pageLimit = 5;    // 보여질 페이지 수(하단 페이지 번호)
        PageInfo pageInfo = PaginationController.getPageInfo(listCount, pageNum, pageLimit, boardLimit);


        model.addAttribute("terms", terms);
        model.addAttribute("searchType", searchType);
        model.addAttribute("pi", pageInfo);

        return "home";
    }


    /*------------------------------등록 페이지 & 상세 페이지--------------------------------------*/
    @GetMapping(value = {"/detail/", "/detail/{strNo}", "/detail/{strNo}/modify"})
    public String detail(@PathVariable("strNo") Optional<String> strNo,
                         @RequestParam(value = "modify", defaultValue = "detail") String modify,
                         Model model) {

        if (strNo.isPresent()) { // 상세페이지
            int no = Integer.parseInt(strNo.get());
//            List<TermDtl> list = new ArrayList<>();
            /*--------------9.20 수정 전 코드----------------------*/
//            list.add(termService.findConts(no, "ko"));
//            list.add(termService.findConts(no, "en"));
//            list.add(termService.findConts(no, "cn"));
//            list.add(termService.findConts(no, "jp"));
            /*-----------------수정 후 코드-----------------------*/
            Term term = termService.findOne(no);
            model.addAttribute("term", term);
            model.addAttribute("list", termService.findConts(no));

            model.addAttribute("modify", null);
            if (!"detail".equals(modify)) {
                model.addAttribute("modify", "modify");
            }
            return "terms/detail";

        } else { //등록 페이지
            model.addAttribute("term", null);
            model.addAttribute("list", null);
            model.addAttribute("modify", null);
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
    public int register(@RequestBody Term term) {
        return termService.register(term);
    }

    /*-------------------------------------- 수정 ----------------------------------------------*/
    @PostMapping(value = "/update")
    @ResponseBody
    public int update(@RequestBody Term term) {
        return termService.update(term);
    }

    /*-------------------------------------- 삭제 ----------------------------------------------*/
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody Term term) {

        int result = termService.delete(term.getNo());
            if(result > 0) {
                System.out.println("확인용1");
                return "success";
            } else  {
                System.out.println("확인용2");
                return "failed";
            }

    }

}