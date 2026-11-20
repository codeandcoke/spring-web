package com.svalero.games.controller;

import com.svalero.games.domain.Review;
import com.svalero.games.dto.ReviewInDto;
import com.svalero.games.dto.ReviewOutDto;
import com.svalero.games.exception.ErrorDTO;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.exception.UserNotFoundException;
import com.svalero.games.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAll() {
        List<Review> allReviews = reviewService.getAll();
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/reviews")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable long userId) throws UserNotFoundException {
        List<Review> userReviews = reviewService.getUserReviews(userId);
        return new ResponseEntity<>(userReviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> get(@PathVariable long reviewId) {
        return null;
    }

    @PostMapping("/users/{userId}/reviews")
    public ResponseEntity<ReviewOutDto> post(@Valid @RequestBody ReviewInDto reviewInDto, @PathVariable long userId) throws
            UserNotFoundException, GameNotFoundException {
        ReviewOutDto reviewOutDto = reviewService.addReview(reviewInDto, userId);
        return new ResponseEntity<>(reviewOutDto, HttpStatus.CREATED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleGameNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setErrorCode(404);
        errorDto.setErrorMessage(userNotFoundException.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleGameNotFoundException(GameNotFoundException gameNotFoundException) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setErrorCode(404);
        errorDto.setErrorMessage(gameNotFoundException.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(400);
        errorDTO.setErrorMessage("Validation error");
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            errorDTO.getErrors().put(((FieldError) error).getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
