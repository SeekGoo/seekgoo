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

    @Transactional
    public Boolean participate(Long seekguIdx, Long memberIdx) {
        SeekguBoard seekguBoard = seekguBoardRepository.getSeekguBoardForUpdate(seekguIdx);
        if (seekguBoard.getSeekguMemberCount() >= seekguBoard.getSeekguMax()) {
            return Boolean.FALSE;
        }
        List<Participant> participants = participantRepository.getParticipantsBySeekguIdx(seekguIdx);
        for(Participant p : participants) {
            if(p.getMemberIdx().equals(memberIdx)) {
                throw new IllegalArgumentException("이미 참여한 멤버입니다.");
            }
        }
        seekguBoardRepository.participate(seekguIdx);
        Participant participant = Participant.builder().memberIdx(memberIdx).seekguIdx(seekguIdx).build();
        participantRepository.saveParticipant(participant);
        return Boolean.TRUE;
    }

}
