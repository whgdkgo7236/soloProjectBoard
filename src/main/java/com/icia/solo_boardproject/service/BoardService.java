package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.BoardDetailDTO;
import com.icia.solo_boardproject.dto.BoardPagingDTO;
import com.icia.solo_boardproject.dto.BoardSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface BoardService {
    Page<BoardPagingDTO> paging(Pageable pageable);

    BoardDetailDTO findById(Long boardid);

    void deleteById(Long boardid);

    Long save(BoardSaveDTO boardSaveDTO) throws IOException;
}
