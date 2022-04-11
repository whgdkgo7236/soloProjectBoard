package com.icia.solo_boardproject.repository;

import com.icia.solo_boardproject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
