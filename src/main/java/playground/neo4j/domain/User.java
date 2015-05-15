package playground.neo4j.domain;

import com.google.common.collect.Sets;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

@NodeEntity
public class User implements BaseObject {

    @GraphId
    Long nodeId;

    @RelatedTo( type = "user", direction = Direction.INCOMING )
//    @Fetch
    private Set<Worker> workers = Sets.newHashSet();

    @Fetch
    Unit currentUnit;

    String name;

    private User() {
    }

    public User( String name ) {
        this.name = name;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId( Long nodeId ) {
        this.nodeId = nodeId;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers( Set<Worker> workers ) {
        this.workers = workers;
    }

    public void addWork( Unit unit, String description ) {
        this.workers.add( new Worker( this, unit, description ) );
    }

    public Unit getCurrentUnit() {
        return currentUnit;
    }

    public void setCurrentUnit( Unit currentUnit ) {
        this.currentUnit = currentUnit;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public boolean isFetched() {
        return name != null;
    }
}
