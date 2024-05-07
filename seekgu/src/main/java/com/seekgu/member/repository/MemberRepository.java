package com.seekgu.member.repository;

import com.seekgu.member.domain.Member;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

@Mapper
public interface MemberRepository {
    Long saveMember(Member member) throws DuplicateKeyException;
    Member getMemberByIdx(Long memberIdx);
    Optional<Member> getMemberById(String memberId);
}
