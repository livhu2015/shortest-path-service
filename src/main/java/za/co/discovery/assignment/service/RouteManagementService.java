package za.co.discovery.assignment.service;

import org.springframework.stereotype.Service;
import za.co.discovery.assignment.model.entity.Route;

import java.util.List;

@Service
public interface RouteManagementService {

    List<Route> getAllRoutes();

    void saveRoute(Route route);

    void updateRoute(Route route);

    void deleteRoute(Long routeId);
}
