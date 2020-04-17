package za.co.discovery.assignment.service;

import org.springframework.stereotype.Service;
import za.co.discovery.assignment.model.entity.Planet;

import java.util.List;

@Service
public interface PlanetManagementService {

    List<Planet> getAllPlanets();

    Planet getPlanet(Long planetId);

    void savePlanet(Planet planet);

    void updatePlanet(Planet planet);

    void deletePlanet(Long planetId);
}
