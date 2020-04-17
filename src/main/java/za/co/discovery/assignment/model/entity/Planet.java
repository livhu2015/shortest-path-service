package za.co.discovery.assignment.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String node;

    public Planet() {}

    public Planet(String node, String name) {
        this.node = node;
        this.name = name;
    }
}
