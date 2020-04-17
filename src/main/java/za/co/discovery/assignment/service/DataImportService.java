package za.co.discovery.assignment.service;

import org.springframework.stereotype.Service;

@Service
public interface DataImportService {

    String importPlanetDataIntoDb() throws Exception;

    String importRouteDataIntoDb() throws Exception;

}
