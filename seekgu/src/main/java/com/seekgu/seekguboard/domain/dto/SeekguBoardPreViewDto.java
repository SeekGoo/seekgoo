package com.seekgu.seekguboard.domain.dto;

import com.seekgu.seekguboard.domain.SeekguBoard;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SeekguBoardPreViewDto {
    private Long boardIdx;
    private String boardTitle;
    private String boardRestaurantName;
    private String boardMealTime;
    private Integer boardMemberCount;
    private Integer boardMax;
    private LocalDateTime boardStartTime;
    private Integer boardLimitTime;
    private Boolean isRecruiting;

    public SeekguBoardPreViewDto(SeekguBoard seekguBoard) {
        this.boardIdx = seekguBoard.getSeekguIdx();
        this.boardTitle = seekguBoard.getSeekguTitle();
        this.boardRestaurantName = seekguBoard.getSeekguRestaurantName();
        this.boardMealTime = seekguBoard.getSeekguMealTime().name();
        this.boardMemberCount = seekguBoard.getSeekguMemberCount();
        this.boardMax = seekguBoard.getSeekguMax();
        this.boardStartTime = seekguBoard.getSeekguRegDate();
        this.boardLimitTime = seekguBoard.getSeekguLimitTime();
        this.isRecruiting = checkRecruiting();
    }

    private Boolean checkRecruiting() {
        return LocalDateTime.now().isBefore(boardStartTime.plusMinutes(boardLimitTime))
                && this.boardMemberCount < this.boardMax;
    }

}
