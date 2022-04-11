package com.icia.solo_boardproject.repository;

import com.icia.solo_boardproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    MemberEntity findByMemberEmail(String memberEmail);
}
