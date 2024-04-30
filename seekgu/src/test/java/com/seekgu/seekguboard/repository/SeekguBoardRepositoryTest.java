package com.seekgu.seekguboard.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.seekgu.seekguboard.domain.MealTime;
import com.seekgu.seekguboard.domain.SeekguBoard;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SeekguBoardRepositoryTest {

    @Autowired
    private SeekguBoardRepository seekguBoardRepository;

    @Test
    void findSeekguBoardByIdx() {
        SeekguBoard seekguBoardByIdx = seekguBoardRepository.findSeekguBoardByIdx(1L);
        Assertions.assertThat(seekguBoardByIdx).isNotNull();
    }

    @Test
    @DisplayName("모집중인 식구모집글 조회 테스트")
    void findRecruitingSeekguBoard() {
        List<SeekguBoard> recruitingSeekguBoard = seekguBoardRepository.findRecruitingSeekguBoard();
        Assertions.assertThat(recruitingSeekguBoard.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("모집글 생성 테스트")
    void save() {
        SeekguBoard seekguBoard = SeekguBoard.builder()
                .seekguTitle("같이 밥 먹으러 가요 ❗️")
                .seekguContent("오늘 점심 탐광에서 밥먹을사람~")
                .seekguRestaurantName("탐광")
                .seekguRestaurantLatitude(123.1)
                .seekguRestaurantLongitude(12.1)
                .seekguMemberCount(1)
                .memberIdx(1L)
                .seekguMin(2)
                .seekguMax(4)
                .seekguLimitTime(10)
                .seekguMealTime(MealTime.LUNCH)
                .build();

        Assertions.assertThat(seekguBoardRepository.save(seekguBoard)).isNotNull();
    }

    @Test
    @DisplayName("모집이 끝난 모집글 조회 테스트")
    void findDoneSeekguBoard() {
        List<SeekguBoard> doneSeekguBoard = seekguBoardRepository.findDoneSeekguBoard();
        Assertions.assertThat(doneSeekguBoard.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("나의 식구 모집글 조회 테스트")
    void findMySeekguBoard() {
        List<SeekguBoard> mySeekguBoard = seekguBoardRepository.findMySeekguBoard(1L);
        Assertions.assertThat(mySeekguBoard.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void participate() {
        Integer seekguMemberCount1 = seekguBoardRepository.findSeekguBoardByIdx(1L).getSeekguMemberCount();
        seekguBoardRepository.participate(1L);

        Integer seekguMemberCount2 = seekguBoardRepository.findSeekguBoardByIdx(1L).getSeekguMemberCount();
        Assertions.assertThat(seekguMemberCount1 + 1).isEqualTo(seekguMemberCount2);
    }
}