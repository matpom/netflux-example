package us.pomorscy.spring.reactive.netflux.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import us.pomorscy.spring.reactive.netflux.domain.Movie;
import us.pomorscy.spring.reactive.netflux.domain.MovieEvent;
import us.pomorscy.spring.reactive.netflux.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public Flux<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }

  @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
    return movieService.events(id);
  }

  @GetMapping("/{id}")
  public Mono<Movie> getById(@PathVariable String id) {
    return movieService.getMovieById(id);
  }

}
