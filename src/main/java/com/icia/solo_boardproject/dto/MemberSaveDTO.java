package com.icia.solo_boardproject.dto;

import com.icia.solo_boardproject.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberSaveDTO {
    private Long id;
    private String origFilename;
    private String memberFilename;
    private MultipartFile memberFile;
    private String filePath;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhonenum;

    public static MemberSaveDTO toSaveDTO(MemberEntity memberEntity){
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setId(memberEntity.getId());
        memberSaveDTO.setOrigFilename(memberEntity.getOrigFilename());
        memberSaveDTO.setMemberFilename(memberEntity.getFilename());

        memberSaveDTO.setFilePath(memberEntity.getFilePath());
        memberSaveDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberSaveDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberSaveDTO.setMemberName(memberEntity.getMemberName());
        memberSaveDTO.setMemberPhonenum(memberEntity.getMemberPhonenum());

        return memberSaveDTO;
    }

}
