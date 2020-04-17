package za.co.discovery.assignment.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Route {

    @Id
    private long id;
    private String source;
    private String destination;
    private Float distance;

    public Route() {
    }

    public Route(Long id, String source, String destination, Float distance) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }
}
