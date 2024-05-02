package com.seekgu.participant.repository;

import com.seekgu.participant.domain.Participant;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ParticipantRepository {
    List<Participant> getParticipantsBySeekguIdx(Long seekguIdx);
    Long saveParticipant(Participant participant);
    Integer checkMemberIsSeekgu(@Param("memberIdx") Long memberIdx, @Param("seekguIdx") Long seekguIdx);

}
