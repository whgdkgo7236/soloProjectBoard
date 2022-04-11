package com.icia.solo_boardproject.repository;

import com.icia.solo_boardproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
