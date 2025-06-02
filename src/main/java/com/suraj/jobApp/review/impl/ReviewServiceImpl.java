package com.suraj.jobApp.review.impl;

import com.suraj.jobApp.review.Review;
import com.suraj.jobApp.review.ReviewRepository;
import com.suraj.jobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteReviewById(Long id) {
        try {
            reviewRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateReviewById(Long id, Review updatedReview) {
        Optional<Review>reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            review.setTitle(updatedReview.getTitle());
            review.setRating(updatedReview.getRating());
            review.setCompany(updatedReview.getCompany());
            review.setDescription(updatedReview.getDescription());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }
}
