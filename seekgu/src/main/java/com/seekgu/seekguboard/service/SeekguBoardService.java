package com.seekgu.seekguboard.service;

import com.seekgu.participant.domain.Participant;
import com.seekgu.participant.repository.ParticipantRepository;
import com.seekgu.seekguboard.domain.SeekguBoard;
import com.seekgu.seekguboard.repository.SeekguBoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeekguBoardService {

    private final SeekguBoardRepository seekguBoardRepository;
    private final ParticipantRepository participantRepository;

    @Transactional
    public Boolean createSeekguBoard(SeekguBoard seekguBoard) {
        try {
            Long saveIdx = seekguBoardRepository.save(seekguBoard);
            Participant participant = Participant.builder()
                    .memberIdx(seekguBoard.getMemberIdx())
                    .seekguIdx(saveIdx)
                    .build();
            participantRepository.saveParticipant(participant);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<SeekguBoard> mySeekguBoards(Long memberIdx) {
        return seekguBoardRepository.findMySeekguBoard(memberIdx);
    }

    public List<SeekguBoard> recruitingSeekguBoards() {
        return seekguBoardRepository.findRecruitingSeekguBoard();
    }

    public List<SeekguBoard> doneSeekguBoards() {
        return seekguBoardRepository.findDoneSeekguBoard();
    }

    public SeekguBoard findSeekguBoardById(Long seekguIdx) {
        return seekguBoardRepository.findSeekguBoardByIdx(seekguIdx);
    }
}
