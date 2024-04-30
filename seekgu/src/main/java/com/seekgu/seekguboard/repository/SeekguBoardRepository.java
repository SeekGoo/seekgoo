package com.seekgu.seekguboard.repository;

import com.seekgu.seekguboard.domain.SeekguBoard;
import com.seekgu.seekguboard.domain.dto.SeekguBoardDetailDto;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SeekguBoardRepository {
    SeekguBoard findSeekguBoardByIdx(Long seekguIdx);
    Long save(SeekguBoard seekguBoard);
    List<SeekguBoard> findRecruitingSeekguBoard();
    List<SeekguBoard> findDoneSeekguBoard();
    List<SeekguBoard> findMySeekguBoard(Long memberIdx);
    SeekguBoard getSeekguBoardForUpdate(Long seekguIdx);
    Boolean participate(Long seekguIdx);
    Optional<SeekguBoardDetailDto> findSeekguBoardWithReviews(Long seekguIdx);
}
