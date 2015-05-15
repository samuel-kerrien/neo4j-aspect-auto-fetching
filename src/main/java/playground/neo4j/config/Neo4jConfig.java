package playground.neo4j.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan( basePackages = "playground.neo4j" )
@EnableNeo4jRepositories( basePackages = "playground.neo4j.repository" )
@EnableTransactionManagement
@EnableLoadTimeWeaving
@EnableAspectJAutoProxy
public class Neo4jConfig extends Neo4jConfiguration {

    public Neo4jConfig() {
        setBasePackage( "playground.neo4j" );
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase( "neo4j.db" );
    }
}
