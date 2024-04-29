package com.seekgu.seekguboard.repository;

import com.seekgu.seekguboard.domain.SeekguBoard;
import java.util.List;
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
}
