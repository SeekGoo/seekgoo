package com.seekgu.seekguboard.api;

import com.seekgu.seekguboard.service.SeekguBoardService;
import com.seekgu.utils.ApiUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @PostMapping("/participate")
    public ApiUtil.ApiSuccessResult<Boolean> participate(
        @RequestParam(name = "seekguIdx") Long seekguIdx,
        HttpSession session)
    {
        Long memberIdx = (Long)session.getAttribute("memberId");
        Boolean participate = seekguBoardService.participate(seekguIdx, memberIdx);
        return ApiUtil.success(participate);
    }
}
