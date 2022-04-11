package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.CommentDetailDTO;
import com.icia.solo_boardproject.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long commentId);

    void delete(Long commentId);
}
