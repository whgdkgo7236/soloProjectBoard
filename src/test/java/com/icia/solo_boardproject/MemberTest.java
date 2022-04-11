package com.icia.solo_boardproject;

import com.icia.solo_boardproject.dto.MemberSaveDTO;
import com.icia.solo_boardproject.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {
    @Autowired
    MemberService ms;

    @Test
    @DisplayName("회원생성")
    public void addMember(){

    }
}
