package com.seekgu.seekguboard.service;

import com.seekgu.member.domain.Member;
import com.seekgu.member.repository.MemberRepository;
import com.seekgu.participant.domain.Participant;
import com.seekgu.participant.repository.ParticipantRepository;
import com.seekgu.seekguboard.domain.SeekguBoard;
import com.seekgu.seekguboard.domain.dto.SeekguBoardCreateDto;
import com.seekgu.seekguboard.domain.dto.SeekguBoardDetailDto;
import com.seekgu.seekguboard.repository.SeekguBoardRepository;
import com.seekgu.utils.slack.SlackUtil;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeekguBoardService {

    private final SeekguBoardRepository seekguBoardRepository;
    private final ParticipantRepository participantRepository;
    private final MemberRepository memberRepository;
    private final SlackUtil slackUtil;

    @Transactional
    public Boolean createSeekguBoard(SeekguBoardCreateDto dto) {
        SeekguBoard seekguBoard = SeekguBoard.builder()
                .seekguTitle(dto.getSeekguTitle())
                .seekguContent(dto.getSeekguContent())
                .seekguRestaurantName(dto.getSeekguRestaurantName())
                .seekguRestaurantLatitude(dto.getSeekguRestaurantLatitude())
                .seekguRestaurantLongitude(dto.getSeekguRestaurantLongitude())
                .memberIdx(dto.getMemberIdx())
                .seekguMin(dto.getSeekguMin())
                .seekguMax(dto.getSeekguMax())
                .seekguLimitTime(dto.getSeekguLimitTime())
                .seekguMealTime(dto.getSeekguMealTime())
                .build();
        try {
            Long saveIdx = seekguBoardRepository.save(seekguBoard);
            Participant participant = Participant.builder()
                    .memberIdx(seekguBoard.getMemberIdx())
                    .seekguIdx(saveIdx)
                    .build();
            participantRepository.saveParticipant(participant);
            sendCreateNoti(seekguBoard.getMemberIdx());
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void sendCreateNoti(Long memberIdx) {
        Member member = memberRepository.getMemberByIdx(memberIdx);
        slackUtil.sendRecruitmentStartNoti(member.getMemberNickName());
    }

    public List<SeekguBoard> mySeekguBoards(Long memberIdx) {
        return seekguBoardRepository.findMySeekguBoard(memberIdx);
    }

    public List<SeekguBoard> recruitingSeekguBoards() {
        return seekguBoardRepository.findRecruitingSeekguBoard();
    }

    public List<SeekguBoard> doneSeekguBoards() {
        return seekguBoardRepository.findDoneSeekguBoard();
    }

    public SeekguBoard findSeekguBoardById(Long seekguIdx) {
        return seekguBoardRepository.findSeekguBoardByIdx(seekguIdx);
    }

    public SeekguBoardDetailDto findSeekguBoardWithReviewById(Long seekguIdx) {
        SeekguBoardDetailDto seekguBoardWithReviews = seekguBoardRepository.findSeekguBoardWithReviews(seekguIdx);
        if (seekguBoardWithReviews.getReviewList().get(0).getReviewIdx() == null) {
            seekguBoardWithReviews.setReviewList(new ArrayList<>());
        }
        return seekguBoardWithReviews;
    }


    @Transactional
    public Boolean participate(Long seekguIdx, Long memberIdx) {
        SeekguBoard seekguBoard = seekguBoardRepository.getSeekguBoardForUpdate(seekguIdx);
        if (seekguBoard.getSeekguMemberCount() >= seekguBoard.getSeekguMax()) {
            return Boolean.FALSE;
        }
        List<Participant> participants = participantRepository.getParticipantsBySeekguIdx(seekguIdx);
        for (Participant p : participants) {
            if (p.getMemberIdx().equals(memberIdx)) {
                throw new IllegalArgumentException("이미 참여한 멤버입니다.");
            }
        }
        seekguBoardRepository.participate(seekguIdx);
        Participant participant = Participant.builder().memberIdx(memberIdx).seekguIdx(seekguIdx).build();
        participantRepository.saveParticipant(participant);
        return Boolean.TRUE;
    }

}
