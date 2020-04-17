package za.co.discovery.assignment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.model.entity.Planet;
import za.co.discovery.assignment.model.entity.Route;
import za.co.discovery.assignment.model.entity.ShortestPath;
import za.co.discovery.assignment.model.Graph;
import za.co.discovery.assignment.model.Node;
import za.co.discovery.assignment.repository.ShortestPathRepository;
import za.co.discovery.assignment.service.PlanetManagementService;
import za.co.discovery.assignment.service.RouteManagementService;
import za.co.discovery.assignment.service.ShortestPathService;

import java.util.*;

@Component
public class ShortestPathServiceImpl implements ShortestPathService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortestPathServiceImpl.class);

    @Autowired
    private PlanetManagementService planetManagementService;
    @Autowired
    private RouteManagementService routeManagementService;
    @Autowired
    private ShortestPathRepository shortestPathRepository;

    public String shortestPath(String sourceNode, String destinationNode) {
        LOGGER.info("Finding the shortest path...");

        List<Planet> planets = planetManagementService.getAllPlanets();

        List<Node> listNode = new ArrayList<>();
        planets.forEach(s -> {
            Node node = new Node(s.getNode());
            listNode.add(node);
        });

        List<Route> routes = routeManagementService.getAllRoutes();
        listNode.forEach(n -> {
            addDestination(n, listNode, routes);
        });

        Graph graph = new Graph();
        for (Node node : listNode) {
            graph.addNode(node);
        }
        graph = dijkstraAlgorithm(graph, listNode.get(0));

        StringBuffer sb = new StringBuffer();
        for( Node node:graph.getNodes()) {

            if(node.getName().equalsIgnoreCase(destinationNode)) {
                for(Node n: node.getShortestPath()) {
                    sb.append(n.getName()).append("->");
                }
            }

        }

        for (Planet planet : planets) {
            ShortestPath shortestPath = new ShortestPath();
            for (Node node : graph.getNodes()) {
                if (node.getName().equalsIgnoreCase(planet.getNode())) {
                    shortestPath.setPlanetNode(planet.getNode());
                    shortestPath.setPlanetName(planet.getName());
                    shortestPath.setPath(node.getPath());
                }

            }
            shortestPathRepository.save(shortestPath);
        }
        return sb.append(destinationNode).toString();
    }

    private static void addDestination(Node n, List<Node> listNode, List<Route> routes) {
        LOGGER.info("add destination...");
        routes.forEach(r -> {
            if (r.getSource().equalsIgnoreCase(n.getName())) {
                listNode.forEach(l -> {
                    if (l.getName().equalsIgnoreCase(r.getDestination())) {
                        n.addDestination(l, r.getDistance());
                    }
                });
            }
        });
    }

                    private static Graph dijkstraAlgorithm(Graph graph, Node source) {
        LOGGER.info("calculate shortest path from source...");
        source.setDistance(0f);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Float> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Float edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void CalculateMinimumDistance(Node evaluationNode, Float edgeWeigh, Node sourceNode) {
        LOGGER.info("calculate minimum distance...");
        Float sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        LOGGER.info("get a lowest distance node...");
        Node lowestDistanceNode = null;
        Float lowestDistance = Float.MAX_VALUE;
        for (Node node : unsettledNodes) {
            Float nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

}
