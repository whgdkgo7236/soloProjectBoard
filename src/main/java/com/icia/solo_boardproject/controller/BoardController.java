package com.icia.solo_boardproject.controller;

import com.icia.solo_boardproject.dto.*;
import com.icia.solo_boardproject.service.BoardService;
import com.icia.solo_boardproject.service.CommentService;
import com.icia.solo_boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
    private final BoardService bs;
    private final MemberService ms;
    private final CommentService cs;


    public static  final int BLOCK_LIMIT = 3;

    @GetMapping("")
    public String Paging(@PageableDefault(page = 1) Pageable pageable, Model model){

        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList",boardList);

        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
        int endPage = ((startPage + BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);


        return "/board/paging";
    }
    @GetMapping("{boardid}")
    public String detail(@PathVariable Long boardid, Model model){
        System.out.println(boardid);
        BoardDetailDTO boardDetailDTO = bs.findById(boardid);
        boardDetailDTO.setBoardHits(boardDetailDTO.getBoardHits()+1);
        model.addAttribute("boardList",boardDetailDTO);
        String sessionEmail=ms.findSessionEmail();
        model.addAttribute("sessionEmail",sessionEmail);

        List<CommentDetailDTO> commentList= cs.findAll(boardid);
        model.addAttribute("commentList",commentList);
        return "/board/detail";
    }
    @DeleteMapping("{boardid}")
    public ResponseEntity delete(@PathVariable Long boardid){
        bs.deleteById(boardid);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("save")
    public String saveform(Model model){
        model.addAttribute("email",ms.findSessionEmail());
        return "/board/save";
    }
    @PostMapping("save")
    public String Save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {
        boardSaveDTO.setId(ms.findSessionId());
        boardSaveDTO.setBoardHits(0L);

        Long boardId=bs.save(boardSaveDTO);
        return "redirect:/board/";
    }
    @GetMapping("update/{boardId}")
    public String updateForm(@PathVariable Long boardId,Model model){
        BoardDetailDTO boardDetailDTO=bs.findById(boardId);
        model.addAttribute("boardList",boardDetailDTO);


        return "/board/update";
    }
    @PostMapping("update")
    public String update(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {

        Long boardId=bs.save(boardSaveDTO);

        return "redirect:/board/"+boardId;
    }

}
