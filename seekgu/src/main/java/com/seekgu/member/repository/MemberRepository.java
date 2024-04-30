package com.seekgu.member.repository;

import com.seekgu.member.domain.Member;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
    Long saveMember(Member member);
    Member getMemberByIdx(Long memberIdx);
    Optional<Member> getMemberById(String memberId);
}
