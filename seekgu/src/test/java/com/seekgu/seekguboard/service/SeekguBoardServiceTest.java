package com.seekgu.seekguboard.service;

import static org.junit.jupiter.api.Assertions.*;

import com.seekgu.seekguboard.domain.MealTime;
import com.seekgu.seekguboard.domain.SeekguBoard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeekguBoardServiceTest {

    @Autowired
    private SeekguBoardService seekguBoardService;

    @Test
    void createSeekguBoard() {
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

        Assertions.assertThat(seekguBoardService.createSeekguBoard(seekguBoard)).isTrue();
    }
}