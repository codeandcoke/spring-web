package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.Review;
import com.svalero.games.domain.User;
import com.svalero.games.dto.ReviewInDto;
import com.svalero.games.dto.ReviewOutDto;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.exception.UserNotFoundException;
import com.svalero.games.repository.GameRepository;
import com.svalero.games.repository.ReviewRepository;
import com.svalero.games.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public List<Review> getUserReviews(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<Review> userReviews = reviewRepository.findByUser(user);
        return userReviews;
    }

    public ReviewOutDto addReview(ReviewInDto reviewInDto, long userId) throws UserNotFoundException, GameNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Game game = gameRepository.findById(reviewInDto.getGameId())
                .orElseThrow(GameNotFoundException::new);

        Review review = new Review();
        review.setUser(user);
        review.setGame(game);
        review.setDate(LocalDate.now());
        review.setComment(reviewInDto.getComment());
        review.setRate(reviewInDto.getRate());

        reviewRepository.save(review);

        ReviewOutDto reviewOutDto = new ReviewOutDto();
        reviewOutDto.setId(review.getId());
        reviewOutDto.setComment(review.getComment());
        reviewOutDto.setRate(review.getRate());
        reviewOutDto.setDate(review.getDate());
        reviewOutDto.setUserId(user.getId());
        reviewOutDto.setGameId(game.getId());

        return reviewOutDto;
    }
}
