package playground.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import playground.neo4j.domain.User;

public interface UserRepository extends GraphRepository<User> {

    Iterable<User> findByName( String name );

}
