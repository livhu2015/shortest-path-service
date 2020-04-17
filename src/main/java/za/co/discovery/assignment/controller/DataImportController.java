package za.co.discovery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.assignment.service.DataImportService;

@RestController
public class DataImportController {

    @Autowired
    private DataImportService dataImportService;

    @GetMapping("/import/routes")
    public String importRoutes() throws Exception {

        return dataImportService.importRouteDataIntoDb();
    }

    @GetMapping("/import/planets")
    public String importPlanets() throws Exception {

        return dataImportService.importPlanetDataIntoDb();
    }
}
