package com.suraj.jobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private List<Review> reviews = new ArrayList<>();
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getAllReviews(id), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId,@RequestBody Review review){
        boolean isReviewSaved = reviewService.createReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review created", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not saved", HttpStatus.BAD_REQUEST);
        }
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
