package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.MemberLoginDTO;
import com.icia.solo_boardproject.dto.MemberPagingDTO;
import com.icia.solo_boardproject.dto.MemberSaveDTO;
import com.icia.solo_boardproject.entity.MemberEntity;
import com.icia.solo_boardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;



@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final HttpSession session;
    private final MemberRepository mr;

    public static  final int PAGE_LIMIT = 3;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        MultipartFile m_file = memberSaveDTO.getMemberFile();

        String m_filename = m_file.getOriginalFilename();
        memberSaveDTO.setOrigFilename(m_filename);
        m_filename = System.currentTimeMillis()+"_"+m_filename;
        memberSaveDTO.setMemberFilename(m_filename);
        String savePath = "C:\\code\\springboot\\springboots\\Solo_BoardProject\\src\\main\\resources\\static\\imgs\\"+m_filename;

        //String savePath = "/imgs/"+m_filename;
        memberSaveDTO.setFilePath(savePath);
        if(!m_file.isEmpty()){
            m_file.transferTo(new File(savePath));
        }

        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberSaveDTO);
        long memberId=mr.save(memberEntity).getId();
        return memberId;
    }

    @Override
    public String emailDuplicate(MemberLoginDTO memberLoginDTO) {
        //MemberEntity entity = MemberEntity.toLoginEntity(memberLoginDTO);
        System.out.println("emailDuple = "+ memberLoginDTO);
        MemberEntity result =mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        System.out.println("dupli = "+ result);
        String bool;
        if(result ==null){
            bool = "null";
        }else{
            bool = "true";
        }
        return bool;
    }

    @Override
    public Long findByEmail(String m_email) {

        MemberLoginDTO memberLoginDTO =MemberLoginDTO.toLoginDTO(mr.findByMemberEmail(m_email));

        return memberLoginDTO.getId();
    }

    @Override
    public String loginDuplicate(MemberLoginDTO memberLoginDTO) {
        MemberEntity byMemberEmail = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        String result= null;
        if(memberLoginDTO.getMemberEmail().equals(byMemberEmail.getMemberEmail())&&memberLoginDTO.getMemberPassword().equals(byMemberEmail.getMemberPassword())){
            result = "true";
            session.setAttribute("email",byMemberEmail.getMemberEmail());
            session.setAttribute("id",byMemberEmail.getId());
        }else{
            result = "false";
        }
        return result;


    }

    @Override
    public MemberSaveDTO findById(Long memberid) {
        MemberEntity memberEntity = mr.findById(memberid).get();
        MemberSaveDTO memberSaveDTO = MemberSaveDTO.toSaveDTO(memberEntity);
        return memberSaveDTO;
    }

    @Override
    public Long update(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberSaveDTO);
        Long id = mr.save(memberEntity).getId();

        System.out.println("업데이트들어옴 = " +id);
        return id;
    }

    @Override
    public Page<MemberPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();   //페이징처리 JPA로처리할시 0번부터시작
        //요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청페이지에서 -1을한다.
//            page = page -1;
        page = (page==1)? 0:(page-1);
        Page<MemberEntity> memberEntities=mr.findAll(PageRequest.of(page, PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"id")));//"id"는 entity 필드이름!!언드바를인식못함
        Page<MemberPagingDTO> memberList = memberEntities.map(
                member -> new MemberPagingDTO(member.getId(),
                        member.getMemberEmail(),
                        member.getMemberName(),
                        member.getMemberPhonenum())
        );
        return memberList;
    }

    @Override
    public void deleteById(Long memberid) {
        mr.deleteById(memberid);
    }

    @Override
    public String findSessionEmail() {
        return (String)session.getAttribute("email");
    }

    @Override
    public Long findSessionId() {
        return (Long)session.getAttribute("id");
    }


}
