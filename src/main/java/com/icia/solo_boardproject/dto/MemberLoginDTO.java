package com.icia.solo_boardproject.dto;

import com.icia.solo_boardproject.entity.MemberEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MemberLoginDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;

    public static MemberLoginDTO toLoginDTO(MemberEntity memberEntity){
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO();
        memberLoginDTO.setId(memberEntity.getId());
        memberLoginDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberLoginDTO.setMemberPassword(memberEntity.getMemberPassword());
        return memberLoginDTO;
    }

}
