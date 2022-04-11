package com.icia.solo_boardproject.controller;

import com.icia.solo_boardproject.dto.CommentDetailDTO;
import com.icia.solo_boardproject.dto.CommentSaveDTO;
import com.icia.solo_boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService cs;

    @PostMapping("save")
    public @ResponseBody List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO){
        System.out.println("cooment "+ commentSaveDTO);
        Long boardId =cs.save(commentSaveDTO);
        List<CommentDetailDTO> commentList = cs.findAll(boardId);
        return commentList;
    }
    @PostMapping("delete")
    public @ResponseBody String delete(@RequestParam("commentId") Long commentId){
        System.out.println(commentId);
        cs.delete(commentId);
        return "true";
    }
}
