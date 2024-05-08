package com.seekgu.review.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataIntegrityViolationException;

import com.seekgu.review.domain.Review;

@Mapper
public interface ReviewRepository {
	Long save(Review review) throws DataIntegrityViolationException;
}
