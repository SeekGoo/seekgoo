package com.seekgu.seekguboard.service;

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

    @Transactional
    public Boolean createSeekguBoard(SeekguBoard seekguBoard) {
        try {
            return seekguBoardRepository.save(seekguBoard);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
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
