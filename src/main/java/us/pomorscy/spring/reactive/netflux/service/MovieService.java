package us.pomorscy.spring.reactive.netflux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import us.pomorscy.spring.reactive.netflux.domain.Movie;
import us.pomorscy.spring.reactive.netflux.domain.MovieEvent;

public interface MovieService {

  Mono<Movie> getMovieById(String id);

  Flux<MovieEvent> events(String movieId);

  Flux<Movie> getAllMovies();
}
