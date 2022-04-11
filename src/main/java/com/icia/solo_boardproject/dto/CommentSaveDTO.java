package com.icia.solo_boardproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveDTO {
    private Long boardId;
    private String commentWriter;
    private String commentContents;
}
