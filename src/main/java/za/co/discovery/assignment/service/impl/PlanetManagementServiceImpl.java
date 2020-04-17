package za.co.discovery.assignment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.model.entity.Planet;
import za.co.discovery.assignment.repository.PlanetRepository;
import za.co.discovery.assignment.service.PlanetManagementService;

import java.util.List;

@Component
public class PlanetManagementServiceImpl implements PlanetManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetManagementServiceImpl.class);

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public List<Planet> getAllPlanets() {
        LOGGER.info("...Get all the planets...");

        return (List<Planet>) planetRepository.findAll();
    }

    @Override
    public Planet getPlanet(Long planetId) {
        LOGGER.info("...Get the planet "+planetId);

        return planetRepository.findById(planetId).get();

    }

    @Override
    public void savePlanet(Planet planet) {
        LOGGER.info("...Save the planet "+planet.getId());

        planetRepository.save(planet);
    }

    @Override
    public void updatePlanet(Planet planet) {
        LOGGER.info("...Update the planet "+planet.getId());

        Planet updatePlanet = planetRepository.findById(planet.getId()).orElse(null);;
        if (planet.getNode() != null) updatePlanet.setNode(planet.getNode());
        if (planet.getName() != null) updatePlanet.setName(planet.getName());

        planetRepository.save(updatePlanet);
    }

    @Override
    public void deletePlanet(Long planetId) {
        LOGGER.info("...Delete the planet "+planetId);

        planetRepository.deleteById(planetId);
    }

}
