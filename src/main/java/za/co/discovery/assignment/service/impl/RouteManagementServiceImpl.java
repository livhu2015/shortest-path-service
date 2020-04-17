package za.co.discovery.assignment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.model.entity.Route;
import za.co.discovery.assignment.repository.RouteRepository;
import za.co.discovery.assignment.service.RouteManagementService;

import java.util.List;

@Component
public class RouteManagementServiceImpl implements RouteManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteManagementServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes(){
        return (List<Route>) routeRepository.findAll();
    }

    public void saveRoute(Route route) {
        LOGGER.info("...Save the route "+route.getId());

        routeRepository.save(route);
    }

    public void updateRoute(Route route) {
        LOGGER.info("...Update the planet "+route.getId());

        Route updateRoute= routeRepository.findById(route.getId()).orElse(null);;
        updateRoute.setDistance(route.getDistance());
        updateRoute.setSource(route.getSource());
        updateRoute.setDestination(route.getDestination());
        routeRepository.save(updateRoute);
    }

    public void deleteRoute(Long routeId) {
        LOGGER.info("...Delete the route "+routeId);

        routeRepository.deleteById(routeId);
    }

}
