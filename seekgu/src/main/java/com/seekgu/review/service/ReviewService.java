package com.seekgu.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;
import com.seekgu.participant.repository.ParticipantRepository;
import com.seekgu.review.domain.Review;
import com.seekgu.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

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
		} catch (Exception e) {
			return Boolean.FALSE;
			}
		return Boolean.TRUE;
	}

	private void checkMemberIsSeekgu(Long memberIdx, Long seekguIdx) {
		Integer isSeekgu = participantRepository.checkMemberIsSeekgu(memberIdx, seekguIdx);
		if(isSeekgu == 0) {
			throw new RuntimeException("NO_PERMISSIO_TO_REVIEW");
		}
	}

}
