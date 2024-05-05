package com.seekgu.seekguboard.api;

import com.seekgu.member.domain.dto.MemberSignUpDto;
import com.seekgu.seekguboard.domain.dto.SeekguBoardCreateDto;
import com.seekgu.seekguboard.domain.dto.SeekguBoardPreViewDto;
import com.seekgu.utils.ApiUtil;
import com.seekgu.seekguboard.domain.SeekguBoard;
import com.seekgu.seekguboard.service.SeekguBoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/seekgu")
public class SeekguBoardApi {
    private final SeekguBoardService seekguBoardService;

    @GetMapping
    public String getRecruitingBoard(Model model) {
        model.addAttribute("seekguList", seekguBoardService.recruitingSeekguBoards());
        return "index";
    }

    @GetMapping("/done")
    public String getDoneBoard(Model model) {
        model.addAttribute("seekguList", seekguBoardService.doneSeekguBoards());
        return "index";
    }

    @GetMapping("/my")
    public String getMyBoard(@RequestParam(name = "memberIdx") Long memberIdx, Model model) {
        model.addAttribute("seekguList",seekguBoardService.mySeekguBoards(memberIdx));
        return "index";
    }

    @GetMapping("/detail")
    public String getDetailBoard(@RequestParam(name = "seekguIdx") Long seekguIdx, Model model) {
        model.addAttribute("seekguBoard", seekguBoardService.findSeekguBoardWithReviewById(seekguIdx));
        return "comment";
    }

    @GetMapping("/write")
    public String boardWrite(){
        return "seekgooboardwrite";
    }

    @ResponseBody
    @PostMapping("/writeImpl")
    public ApiUtil.ApiSuccessResult<Boolean> writeImpl(@RequestBody SeekguBoardCreateDto dto) {
        return ApiUtil.success(seekguBoardService.createSeekguBoard(dto));
    }
}
