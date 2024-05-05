package com.seekgu.seekguboard.service;

import com.seekgu.seekguboard.domain.MealTime;
import com.seekgu.seekguboard.domain.dto.SeekguBoardCreateDto;
import com.seekgu.seekguboard.domain.dto.SeekguBoardDetailDto;

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
        SeekguBoardCreateDto seekguBoard = new SeekguBoardCreateDto(
                "같이 밥 먹으러 가요 ❗️",
                "오늘 점심 탐광에서 밥먹을사람~",
                "탐광",
                123.1,
                12.1,
                1L,
                1,
                4,
                1,
                MealTime.LUNCH
        );

        Assertions.assertThat(seekguBoardService.createSeekguBoard(seekguBoard)).isTrue();
    }
}