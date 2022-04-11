package com.icia.solo_boardproject.dto;

import com.icia.solo_boardproject.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailDTO {
    private Long id;
    private Long boardId;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime createTime;

    public static CommentDetailDTO toCommentDetailDTO(CommentEntity c) {
        CommentDetailDTO commentDetailDTO = new CommentDetailDTO();
        commentDetailDTO.setId(c.getId());
        commentDetailDTO.setCommentWriter(c.getCommentWriter());
        commentDetailDTO.setCommentContents(c.getCommentContents());
        commentDetailDTO.setCreateTime(c.getCreateTime());
        commentDetailDTO.setBoardId(c.getBoardEntity().getId());
        return commentDetailDTO;
    }
}
