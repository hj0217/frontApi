package com.demo1.demo1.main.domain;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageInfo {

    private int listCount;			// 전체 게시글 수
    private int currentPage;		// 현재 페이지
    private int pageLimit;			// 한 페이지에 보여질 페이지의 수
    private int boardLimit;			// 한 페이지에 들어갈 게시글의 수

    private int maxPage;			// 전체 페이지
    private int startPage;			// 시작 페이지
    private int endPage;			// 끝 페이지

    //------   2023-10-17 추가  ------//
    private int offset;

}
