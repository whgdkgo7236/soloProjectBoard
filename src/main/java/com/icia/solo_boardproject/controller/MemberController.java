package com.icia.solo_boardproject.controller;

import com.icia.solo_boardproject.dto.MemberLoginDTO;
import com.icia.solo_boardproject.dto.MemberPagingDTO;
import com.icia.solo_boardproject.dto.MemberSaveDTO;
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

import javax.websocket.server.PathParam;
import java.io.IOException;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;


    public static  final int BLOCK_LIMIT = 3;

    @GetMapping("save")
    public String saveForm(){
        return "/member/save";
    }
    @PostMapping("save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) throws IOException {
        System.out.println("memberSaveDTO = " + memberSaveDTO);
        Long memberId=ms.save(memberSaveDTO);
        return "index";
    }
    @GetMapping("login")
    public String loginForm(){
     return "/member/login";
    }
    @PostMapping("login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, Model model){

        Long memberId =ms.findByEmail(memberLoginDTO.getMemberEmail());
        memberLoginDTO.setId(memberId);
        model.addAttribute("member",memberLoginDTO);

        return "/member/main";
    }

    @PostMapping("loginDupl")
    public @ResponseBody String loginDuple(@ModelAttribute MemberLoginDTO memberLoginDTO){
        String result = ms.loginDuplicate(memberLoginDTO);
        return  result;
    }
    @PostMapping("emailDuplicate")
    public @ResponseBody String emailcheck(@ModelAttribute MemberLoginDTO memberLoginDTO){
        System.out.println("memberLoginDTO = " + memberLoginDTO);
        return ms.emailDuplicate(memberLoginDTO);
    }

    @GetMapping("update/{memberid}")
    public String updateForm(@PathVariable Long memberid,Model model){
           MemberSaveDTO memberSaveDTO= ms.findById(memberid);
        System.out.println("filepath = "+memberSaveDTO.getFilePath()+" memberfilename = "+memberSaveDTO.getMemberFilename()+" memberOrigFileName = "+memberSaveDTO.getOrigFilename());
           model.addAttribute("memberDTO",memberSaveDTO);

        return "/member/update";
    }
    @PostMapping("update")
    public String update(@ModelAttribute MemberSaveDTO memberSaveDTO){
        System.out.println("memberSaveDTO = " + memberSaveDTO);

        ms.update(memberSaveDTO);
        return "redirect:/member/update/"+memberSaveDTO.getId();
    }
    @GetMapping("")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<MemberPagingDTO> memberList = ms.paging(pageable);
        model.addAttribute("memberList",memberList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
        int endPage = ((startPage + BLOCK_LIMIT - 1) < memberList.getTotalPages()) ? startPage + BLOCK_LIMIT - 1 : memberList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "/member/paging";
    }
    @DeleteMapping("{memberid}")
    public ResponseEntity delete(@PathVariable Long memberid){

        ms.deleteById(memberid);
        return new ResponseEntity(HttpStatus.OK);
    }

}
