package com.seekgu.review.repository;

import org.apache.ibatis.annotations.Mapper;

import com.seekgu.review.domain.Review;

@Mapper
public interface ReviewRepository {
	Long save(Review review);
}
