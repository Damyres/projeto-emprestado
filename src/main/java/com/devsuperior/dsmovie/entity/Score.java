package com.devsuperior.dsmovie.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "scores")
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
