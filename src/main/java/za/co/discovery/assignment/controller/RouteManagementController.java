package za.co.discovery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.model.entity.Route;
import za.co.discovery.assignment.service.RouteManagementService;

import java.util.List;

@RestController
public class RouteManagementController {

    @Autowired
    private RouteManagementService routeManagementService;

    @GetMapping("/get/all/routes")
    public List<Route> getAllRoutes() {

        return routeManagementService.getAllRoutes();
    }

    @PostMapping("/save/route")
    public void saveRoute(Route route) {

        routeManagementService.saveRoute(route);
    }

    @PostMapping("/update/route")
    public void updateRoute(Route route) {

        routeManagementService.updateRoute(route);
    }

    @DeleteMapping("/delete/route/{routeId}")
    public void deleteRoute(@PathVariable("routeId") Long routeId) {

        routeManagementService.deleteRoute(routeId);
    }
}
