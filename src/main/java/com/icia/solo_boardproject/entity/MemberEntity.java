package com.icia.solo_boardproject.entity;

import com.icia.solo_boardproject.dto.MemberLoginDTO;
import com.icia.solo_boardproject.dto.MemberSaveDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "membertable")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberPhonenum;

    @Column
    private String origFilename;

    @Column
    private String filename;

    @Column
    private String filePath;

    @OneToMany(mappedBy = "memberEntity",fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();



    @Builder
    public void File(Long id,String origFilename, String filename,String filePath){
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public static MemberEntity toSaveEntity(MemberSaveDTO memberSaveDTO){
        MemberEntity entity = new MemberEntity();
        entity.setId(memberSaveDTO.getId());
        entity.setMemberEmail(memberSaveDTO.getMemberEmail());
        entity.setMemberName(memberSaveDTO.getMemberName());
        entity.setMemberPassword(memberSaveDTO.getMemberPassword());
        entity.setMemberPhonenum(memberSaveDTO.getMemberPhonenum());
        entity.setFilename(memberSaveDTO.getMemberFilename());
        entity.setFilePath(memberSaveDTO.getFilePath());
        entity.setOrigFilename(memberSaveDTO.getOrigFilename());

        return  entity;
    }
    public static MemberEntity toLoginEntity(MemberLoginDTO memberLoginDTO){
        MemberEntity entity = new MemberEntity();
        entity.setMemberEmail(memberLoginDTO.getMemberEmail());
        entity.setId(memberLoginDTO.getId());
        entity.setMemberPassword(memberLoginDTO.getMemberPassword());

        return  entity;
    }
}
