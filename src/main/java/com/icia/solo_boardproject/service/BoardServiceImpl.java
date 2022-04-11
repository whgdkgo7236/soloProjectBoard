package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.BoardDetailDTO;
import com.icia.solo_boardproject.dto.BoardPagingDTO;
import com.icia.solo_boardproject.dto.BoardSaveDTO;
import com.icia.solo_boardproject.dto.MemberPagingDTO;
import com.icia.solo_boardproject.entity.BoardEntity;
import com.icia.solo_boardproject.entity.MemberEntity;
import com.icia.solo_boardproject.repository.BoardRepository;
import com.icia.solo_boardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;
    private final MemberRepository mr;

    public static  final int PAGE_LIMIT = 3;
    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();   //페이징처리 JPA로처리할시 0번부터시작
        //요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청페이지에서 -1을한다.
//            page = page -1;
        page = (page==1)? 0:(page-1);
        Page<BoardEntity> boardEntities=br.findAll(PageRequest.of(page, PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"id")));//"id"는 entity 필드이름!!언드바를인식못함
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardContents(),
                        board.getBoardHits())
        );
        System.out.println("boardService = "+ boardList);
        return boardList;
    }

    @Override
    public BoardDetailDTO findById(Long boardid) {
         BoardEntity boardEntity= br.findById(boardid).get();
        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toboardDetail(boardEntity);
        System.out.println("repository = " + boardDetailDTO);
        return boardDetailDTO;
    }

    @Override
    public void deleteById(Long boardid) {
        br.deleteById(boardid);
    }

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {

        MultipartFile b_file = boardSaveDTO.getBoardFile();
        String b_originfilename = b_file.getOriginalFilename();
        boardSaveDTO.setOrigFilename(b_originfilename);
        String b_filename = System.currentTimeMillis()+"-"+b_originfilename;
        boardSaveDTO.setBoardFilename(b_filename);
        String savePath = "C:\\code\\springboot\\springboots\\Solo_BoardProject\\src\\main\\resources\\static\\imgs\\"+b_filename;
        boardSaveDTO.setFilePath(savePath);
        if(!b_file.isEmpty()){
            b_file.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardFilename(b_filename);


        MemberEntity memberEntity= mr.findById(boardSaveDTO.getId()).get();
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardSaveDTO,memberEntity);

        return br.save(boardEntity).getId();
    }
}
