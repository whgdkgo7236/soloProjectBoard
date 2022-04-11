package com.icia.solo_boardproject.entity;

import com.icia.solo_boardproject.dto.BoardSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="boardtable")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String boardTitle;

    @Column
    private String boardWriter;

    @Column
    private String boardContents;

    @Column
    private Long boardHits;

    @Column
    private String origFilename;

    @Column
    private String filename;

    @Column
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="memberId")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy ="boardEntity",fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static BoardEntity toSaveEntity(BoardSaveDTO boardSaveDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardSaveDTO.getBoardWriter());
        boardEntity.setFilename(boardSaveDTO.getBoardFilename());
        boardEntity.setFilePath(boardSaveDTO.getFilePath());
        boardEntity.setOrigFilename(boardSaveDTO.getOrigFilename());
        boardEntity.setBoardHits(boardSaveDTO.getBoardHits());
        return boardEntity;
    }
}
