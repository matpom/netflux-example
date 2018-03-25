package us.pomorscy.spring.reactive.netflux.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import us.pomorscy.spring.reactive.netflux.domain.Movie;
import us.pomorscy.spring.reactive.netflux.repositories.MovieRepository;

@Slf4j
@Component
public class BootstrapCLR implements CommandLineRunner {

  private final MovieRepository movieRepository;

  public BootstrapCLR(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public void run(String... args) {
    movieRepository.deleteAll()
        .thenMany(
            Flux.just("The Shawshank Redemption",
                "The Godfather",
                "The Godfather: Part II",
                "The Dark Knight",
                "12 Angry Men",
                "Schindler's List",
                "The Lord of the Rings: The Return of the King",
                "Pulp Fiction",
                "The Good, the Bad and the Ugly",
                "FightClub")
                .map(Movie::new)
                .flatMap(movieRepository::save))
        .subscribe(c -> {
        }, e -> {
        }, () -> {
          log.info("Data stored:");
          movieRepository.findAll().subscribe(movie -> log.info(movie.toString()));
        });


  }
}
