package com.seekgu.review.service;

import com.seekgu.review.exception.NotASeekguException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;
import com.seekgu.participant.repository.ParticipantRepository;
import com.seekgu.review.domain.Review;
import com.seekgu.review.exception.DataLengthTooLongException;
import com.seekgu.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ParticipantRepository participantRepository;

	@Transactional
	public Boolean createReview(Long memberIdx, Review review) {
		try {
			checkMemberIsSeekgu(memberIdx, review.getSeekguIdx());
			reviewRepository.save(review);
		} catch (DataIntegrityViolationException e) {
			throw new DataLengthTooLongException("글자수 맞추라고 했잖아");
			}
		return Boolean.TRUE;
	}

	private void checkMemberIsSeekgu(Long memberIdx, Long seekguIdx) {
		Integer isSeekgu = participantRepository.checkMemberIsSeekgu(memberIdx, seekguIdx);
		if(isSeekgu == 0) {
			throw new NotASeekguException("식구만 리뷰를 남길 수 있습니다.");
		}
	}

}
