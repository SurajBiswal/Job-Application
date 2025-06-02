package com.suraj.jobApp.review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Boolean createReview(Long companyId ,Review review);
    Review getReviewById(Long id);
    Boolean deleteReviewById(Long id);
    Boolean updateReviewById(Long id, Review updatedReview);
}
