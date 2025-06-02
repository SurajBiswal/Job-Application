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
    public Boolean createReview(Long companyId ,Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
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
