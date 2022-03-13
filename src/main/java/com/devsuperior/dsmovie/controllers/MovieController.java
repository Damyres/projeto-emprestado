package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService service;

  @GetMapping("/")
  public ResponseEntity<Page<MovieDTO>> findAll(Pageable pageable) {
	return ResponseEntity.ok().body(service.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieDTO> findById(@PathVariable("id") Long id) {
	return ResponseEntity.ok().body(service.findById(id));
  }

}
