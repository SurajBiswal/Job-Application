package com.suraj.jobApp.review.impl;

import com.suraj.jobApp.company.Company;
import com.suraj.jobApp.company.CompanyService;
import com.suraj.jobApp.review.Review;
import com.suraj.jobApp.review.ReviewRepository;
import com.suraj.jobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review>reviews =  reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Long companyId ,Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .map(review -> {
                    reviewRepository.deleteReviewById(review.getId());
                    return true;
                })
                .orElse(false);
    }


    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        Optional<Review> reviewOptional = reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst();

        if (reviewOptional.isPresent()) {
            Review existingReview = reviewOptional.get();
            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setRating(updatedReview.getRating());
            existingReview.setCompany(updatedReview.getCompany());
            existingReview.setDescription(updatedReview.getDescription());
            reviewRepository.save(existingReview);
            return true;
        }

        return false;
    }

}
