package playground.neo4j.domain;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Worker {

    @GraphId
    Long nodeId;

    @Fetch
    User user;

    @Fetch
    Unit unit;

    String description;

    private Worker() {
    }

    public Worker( User user, Unit unit, String description ) {
        this.user = user;
        this.unit = unit;
        this.description = description;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId( Long nodeId ) {
        this.nodeId = nodeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit( Unit unit ) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}
