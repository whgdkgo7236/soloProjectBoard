package com.icia.solo_boardproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private Long boardHits;
    private String origFilename;
    private String boardFilename;
    private MultipartFile boardFile;
    private String filePath;
}
