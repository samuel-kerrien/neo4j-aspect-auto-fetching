package playground.neo4j.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import( Neo4jConfig.class )
public class Neo4jConfigInMemory {

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new TestGraphDatabaseFactory().newImpermanentDatabase();
    }
}
