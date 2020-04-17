package za.co.discovery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.model.entity.Planet;
import za.co.discovery.assignment.service.PlanetManagementService;

import java.util.List;

@RestController
public class PlanetManagementController {

    @Autowired
    private PlanetManagementService planetManagementService;

    @GetMapping("get/all/planets")
    public ResponseEntity<List<Planet>> getAllPlanets() {

        return new ResponseEntity<>(planetManagementService.getAllPlanets(), HttpStatus.OK);
    }

    @PostMapping("/save/planet")
    public void savePlanet(Planet planet) {

        planetManagementService.savePlanet(planet);
    }

    @PostMapping("/update/planet")
    public void updatePlanet(Planet planet) {

        planetManagementService.updatePlanet(planet);
    }

    @DeleteMapping("/delete/planet/{planetId}")
    public void deletePlanet(@PathVariable("planetId") Long planetId) {

        planetManagementService.deletePlanet(planetId);
    }
}
