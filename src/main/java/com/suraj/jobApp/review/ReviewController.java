package com.suraj.jobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private List<Review> reviews = new ArrayList<>();
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review){
        reviewService.createReview(review);
        return new ResponseEntity<>("Review created successfully ", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id){
        Review review = reviewService.getReviewById(id);
        if(review != null){
            return new ResponseEntity<>(review , HttpStatus.OK);
        }else{
            return new ResponseEntity<>(review , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReviewById(@RequestBody Review review, @PathVariable Long id){
        Boolean update = reviewService.updateReviewById(id, review);
        if(update){
            return new ResponseEntity<>(" review updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(" review not exists", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long id){
        Boolean present = reviewService.deleteReviewById(id);
        if(present){
            return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

}
