package za.co.discovery.assignment.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ShortestPath {
    @Id
    @GeneratedValue
    private Long id;
    private String planetNode;
    private String planetName;
    private String path;

    public ShortestPath() {}
}
