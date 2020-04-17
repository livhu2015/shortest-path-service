package za.co.discovery.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.assignment.service.ShortestPathService;

/**
 * @author livhuwanimatsigila
 */

@RestController
public class ShortestPathController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortestPathController.class);

    @Autowired
    private ShortestPathService pathService;

    @PostMapping("/get/shortest-path")
    public @ResponseBody String findShortestPath(String source, String destination) {

        LOGGER.info("Find a shortest path...");

        return pathService.shortestPath(source, destination);
    }
}