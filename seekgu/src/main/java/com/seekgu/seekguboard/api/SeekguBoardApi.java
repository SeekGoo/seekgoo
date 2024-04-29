package com.seekgu.seekguboard.api;

import com.seekgu.ApiUtil;
import com.seekgu.seekguboard.domain.SeekguBoard;
import com.seekgu.seekguboard.service.SeekguBoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/seekgu")
public class SeekguBoardApi {
    private final SeekguBoardService seekguBoardService;

    @GetMapping("/recruit")
    public ApiUtil.ApiSuccessResult<List<SeekguBoard>> getRecruitingBoard() {
        return ApiUtil.success(seekguBoardService.recruitingSeekguBoards());
    }

    @GetMapping("/done")
    public ApiUtil.ApiSuccessResult<List<SeekguBoard>> getDoneBoard() {
        return ApiUtil.success(seekguBoardService.doneSeekguBoards());
    }

    @GetMapping("/my")
    public ApiUtil.ApiSuccessResult<List<SeekguBoard>> getMyBoard(@RequestParam(name = "memberIdx") Long memberIdx) {
        return ApiUtil.success(seekguBoardService.mySeekguBoards(memberIdx));
    }
}
