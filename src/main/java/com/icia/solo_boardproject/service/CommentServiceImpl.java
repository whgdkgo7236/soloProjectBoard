package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.CommentDetailDTO;
import com.icia.solo_boardproject.dto.CommentSaveDTO;
import com.icia.solo_boardproject.entity.BoardEntity;
import com.icia.solo_boardproject.entity.CommentEntity;
import com.icia.solo_boardproject.repository.BoardRepository;
import com.icia.solo_boardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository cr;
    private final BoardRepository br;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        CommentEntity commentEntity=CommentEntity.toSaveEntity(commentSaveDTO,boardEntity);
        return cr.save(commentEntity).getBoardEntity().getId();
    }

    @Override
    public List<CommentDetailDTO> findAll(Long commentId) {
        BoardEntity boardEntity=br.findById(commentId).get();
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        List<CommentDetailDTO> commentList = new ArrayList<>();
        for(CommentEntity c: commentEntityList){
            CommentDetailDTO commentDetailDTO = CommentDetailDTO.toCommentDetailDTO(c);
            commentList.add(commentDetailDTO);
        }

        return commentList;
    }

    @Override
    public void delete(Long commentId) {
        cr.deleteById(commentId);
    }
}
