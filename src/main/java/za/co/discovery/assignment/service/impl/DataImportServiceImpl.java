package za.co.discovery.assignment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.model.entity.Planet;
import za.co.discovery.assignment.model.entity.Route;
import za.co.discovery.assignment.repository.PlanetRepository;
import za.co.discovery.assignment.repository.RouteRepository;
import za.co.discovery.assignment.service.DataImportService;

import java.io.*;
import java.util.List;

@Component
public class DataImportServiceImpl implements DataImportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataImportServiceImpl.class);

    @Value("${route.src.file.path}")
    private String routeSourceFilePath;
    @Value("${planet.src.file.path}")
    private String planetSourceFilePath;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private PlanetRepository planetRepository;


    @Override
    public String importPlanetDataIntoDb() {

        File file = getSourceFile(planetSourceFilePath);

        try {
            LOGGER.info("importing data from the planet source file...");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine = "";
            bufferedReader.readLine();

            while ((readLine = bufferedReader.readLine()) != null) {

                String node = readLine.substring(0, 4).trim();
                String name = readLine.substring(4, readLine.length()).trim();
                Planet planet = new Planet(node, name);

                planetRepository.save(planet);
            }
            LOGGER.info("Done storing data in the database!");

            List<Route> routeList = (List<Route>) routeRepository.findAll();

            LOGGER.info("size of the list is from H2 DB: " + routeList.size());

        } catch (FileNotFoundException ex) {

            LOGGER.error("File not found: " + ex.getMessage());

        } catch (IOException ex) {

            LOGGER.error("I/O Exception: " + ex.getMessage());
        }
        return "File imported successfully";
    }

    @Override
    public String importRouteDataIntoDb() {

        File file = getSourceFile(routeSourceFilePath);

        try {
            LOGGER.info("importing data from the route source file...");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine = "";
            bufferedReader.readLine();

            while ((readLine = bufferedReader.readLine()) != null) {

                long id = Long.parseLong(readLine.substring(0, 4).trim());
                String source = readLine.substring(4, 8).trim();
                String destination = readLine.substring(8, 12).trim();
                float distance = Float.parseFloat(readLine.substring(12).trim());

                routeRepository.save(new Route(id, source, destination, distance));
            }

            LOGGER.info("Done storing data in the database!");

            List<Route> routeList = (List<Route>) routeRepository.findAll();

            LOGGER.info("size of the list is from H2 DB: " + routeList.size());

        } catch (FileNotFoundException ex) {

            LOGGER.error("File not found: " + ex.getMessage());

        } catch (IOException ex) {

            LOGGER.error("I/O Exception: " + ex.getMessage());
        }
        return "File imported successfully";
    }

    private static File getSourceFile(String srcFilePath) {
        File file = null;

        try {
            file = new ClassPathResource(srcFilePath).getFile();

            if (!file.exists()) {
                throw new FileNotFoundException(file.getPath() + " cannot be resolved to URL because it does not exist");
            }

        } catch (FileNotFoundException ex) {
            LOGGER.error("File not found: " + ex.getMessage());

        } catch (IOException ex) {
            LOGGER.error("I/O Exception: " + ex.getMessage());
        }

        return file;
    }


}
