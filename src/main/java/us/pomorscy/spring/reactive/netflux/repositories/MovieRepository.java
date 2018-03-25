package us.pomorscy.spring.reactive.netflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import us.pomorscy.spring.reactive.netflux.domain.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
