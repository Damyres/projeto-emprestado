package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entity.Movie;
import com.devsuperior.dsmovie.entity.Score;
import com.devsuperior.dsmovie.entity.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScoreService {

  private final UserRepository userRepository;
  private final MovieRepository movieRepository;
  private final ScoreRepository scoreRepository;

  @Transactional
  public MovieDTO saveScore(ScoreDTO dto) {
	var optionalUser = userRepository.findByEmail(dto.getEmail());
	var user = new User();
	if(optionalUser.isEmpty()) {
	  user.setEmail(dto.getEmail());
	  user = userRepository.saveAndFlush(user);
	} else {
	  user = optionalUser.get();
	}

	var optionalMovie = movieRepository.findById(dto.getMovieId());
	var movie = new Movie();
	if(optionalMovie.isPresent()) {
	  movie = optionalMovie.get();
	}

	var score = new Score();
	score.setMovie(movie);
	score.setUser(user);
	score.setValue(dto.getScore());

	scoreRepository.saveAndFlush(score);

	double sum = 0;
	for(Score s : movie.getScores()) {
	  sum = sum + s.getValue();
	}

	var avg = sum / movie.getScores().size();
	movie.setScore(avg);
	movie.setCount(movie.getScores().size());

	movie = movieRepository.save(movie);
	return new MovieDTO(movie);
  }

}
