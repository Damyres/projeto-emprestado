package com.devsuperior.dsmovie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "scores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Score {

  @EmbeddedId
  private ScorePK id = new ScorePK();
  private Double value;

  public void setMovie(Movie movie) {
	id.setMovie(movie);
  }

  public void setUser(User user) {
	id.setUser(user);
  }

}
