package za.co.discovery.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.model.entity.ShortestPath;

@Repository
public interface ShortestPathRepository  extends CrudRepository<ShortestPath, Long> {

}
