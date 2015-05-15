package playground.neo4j.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Unit {

    @GraphId
    Long nodeId;

    @Indexed
    int type;

    String description;

    private Unit() {
    }

    public Unit( int type, String description ) {
        this.type = type;
        this.description = description;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId( Long nodeId ) {
        this.nodeId = nodeId;
    }

    public int getType() {
        return type;
    }

    public void setType( int type ) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}
