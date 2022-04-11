package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.MemberLoginDTO;
import com.icia.solo_boardproject.dto.MemberPagingDTO;
import com.icia.solo_boardproject.dto.MemberSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;

    String emailDuplicate(MemberLoginDTO memberLoginDTO);

    Long findByEmail(String m_email);

    String loginDuplicate(MemberLoginDTO memberLoginDTO);

    MemberSaveDTO findById(Long memberid);
    Long update(MemberSaveDTO memberSaveDTO);

    Page<MemberPagingDTO> paging(Pageable pageable);

    void deleteById(Long memberid);


    String findSessionEmail();

    Long findSessionId();
}
