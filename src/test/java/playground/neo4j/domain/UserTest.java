package playground.neo4j.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import playground.neo4j.config.Neo4jConfigInMemory;
import playground.neo4j.repository.UserRepository;

import java.util.Set;

import static junit.framework.Assert.assertEquals;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { Neo4jConfigInMemory.class } )
public class UserTest {

    @Autowired
    public Neo4jTemplate neo;

    @Autowired
    private UserRepository userRepository;

    @Before
    @Transactional
    public void setup() {
        Unit unit = new Unit( 1, "a unit" );
        neo.save( unit );

        User user = new User( "john" );
        user.addWork( unit, "a piece of work" );
        neo.save( user );
    }

    @Test
    @Transactional
    public void testAutoFetching() {
        Iterable<User> users = userRepository.findByName( "john" );
        User reloadedUser = users.iterator().next();
        Set<Worker> workers = reloadedUser.getWorkers();
        assertEquals( 1, workers.size() );
        Worker worker = workers.iterator().next();

        // FIXME User.workers not marked as @Fetch so description doesn't get loaded from neo4j
        assertEquals( "a piece of work", worker.getDescription() );
    }
}