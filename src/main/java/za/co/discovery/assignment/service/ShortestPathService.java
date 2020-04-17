package za.co.discovery.assignment.service;

import org.springframework.stereotype.Service;

@Service
public interface ShortestPathService {

    String shortestPath(String sourceNode, String destinationNode);
}