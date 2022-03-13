package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository repository;

  @Transactional(readOnly = true)
  public Page<MovieDTO> findAll(Pageable pageable) {
	return repository.findAll(pageable).map(MovieDTO::new);
  }

  public MovieDTO findById(Long id) {
	var optional = repository.findById(id);
	if(optional.isEmpty()) {
	  throw new NoSuchElementException("Movie not found");
	}
	return new MovieDTO(optional.get());
  }

}
