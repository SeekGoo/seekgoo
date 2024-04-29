package com.seekgu.participant.repository;

import com.seekgu.participant.domain.Participant;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParticipantRepository {
    List<Participant> getParticipantsBySeekguIdx(Long seekguIdx);
    Long saveParticipant(Participant participant);
}
