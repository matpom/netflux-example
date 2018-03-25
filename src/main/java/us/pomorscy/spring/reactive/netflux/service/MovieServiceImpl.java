package us.pomorscy.spring.reactive.netflux.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import us.pomorscy.spring.reactive.netflux.domain.Movie;
import us.pomorscy.spring.reactive.netflux.domain.MovieEvent;
import us.pomorscy.spring.reactive.netflux.repositories.MovieRepository;

import java.time.Duration;
import java.util.Date;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;

  public MovieServiceImpl(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public Flux<MovieEvent> events(String movieId) {
    return Flux.<MovieEvent>generate(movieEventSynchronousSink ->
        movieEventSynchronousSink.next(new MovieEvent(movieId, new Date())))
        .delayElements(Duration.ofSeconds(1))
        .doOnEach(e -> log.info("Sending event, {}", e.get()));
  }

  @Override
  public Mono<Movie> getMovieById(String id) {
    return movieRepository.findById(id);
  }

  @Override
  public Flux<Movie> getAllMovies() {
    return movieRepository.findAll();
  }
}
