package com.icia.solo_boardproject.dto;

import com.icia.solo_boardproject.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private Long boardHits;
    private String origFilename;
    private String boardFilename;
    private String filePath;


    public static BoardDetailDTO toboardDetail(BoardEntity Entity){
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setId(Entity.getId());
        boardDetailDTO.setBoardTitle(Entity.getBoardTitle());
        boardDetailDTO.setBoardWriter(Entity.getBoardWriter());
        boardDetailDTO.setBoardContents(Entity.getBoardContents());
        boardDetailDTO.setBoardHits(Entity.getBoardHits());
        boardDetailDTO.setBoardFilename(Entity.getFilename());
        boardDetailDTO.setOrigFilename(Entity.getOrigFilename());
        boardDetailDTO.setFilePath(Entity.getFilePath());
        return boardDetailDTO;
    }
}
