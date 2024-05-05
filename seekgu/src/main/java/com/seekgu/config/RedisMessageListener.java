package com.seekgu.config;

import com.seekgu.seekguboard.domain.SeekguBoard;
import com.seekgu.seekguboard.service.SeekguBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisMessageListener implements MessageListener {

    private final SeekguBoardService seekguBoardService;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            long seekguIdx = Long.parseLong(message.toString());
            checkIfBoardDone(seekguIdx);
        } catch (NumberFormatException e) {
            log.error("올바르지 않은 메세지입니다.");
        }
    }

    private void checkIfBoardDone(Long seekguIdx) {
        SeekguBoard seekguBoard = seekguBoardService.findSeekguBoardById(seekguIdx);
        if(seekguBoard.getSeekguMemberCount() >= seekguBoard.getSeekguMin()) {
            seekguBoardService.sendBoardDoneNoti(seekguIdx);
        }
    }
}
