package g58594.atlg4.stibRide.model.graph;

import java.util.*;

public class Node {
    private String name;

    private List<Integer> lines;

    private List<Node> shortestPath = new ArrayList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name, int line) {
        this.name = name;
        this.lines = new ArrayList<>();
        this.lines.add(line);
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(int i) {
        distance = i;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public String getName() {
        return name;
    }

    public void addLine(int line) {
        this.lines.add(line);
    }

    public String getLines() {
        return this.lines.toString();
    }
}
