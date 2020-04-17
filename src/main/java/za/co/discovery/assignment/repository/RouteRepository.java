package za.co.discovery.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.model.entity.Route;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

}
