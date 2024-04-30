package com.seekgu.participant.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.seekgu.participant.domain.Participant;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParticipantRepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;
    @Test
    void getParticipantsBySeekguIdx() {
        List<Participant> participantsBySeekguIdx = participantRepository.getParticipantsBySeekguIdx(1L);
        Assertions.assertThat(participantsBySeekguIdx.size()).isEqualTo(1);
    }

    @Test
    void saveParticipant() {
        Participant participant = Participant.builder()
                .memberIdx(1L)
                .seekguIdx(1L).build();

        Assertions.assertThat(participantRepository.saveParticipant(participant)).isNotNull();
    }
}