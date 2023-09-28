package g58594.atlg4.stibRide.model.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodes;

    public Graph() {
        this.nodes = new HashSet<>();
    }

    public boolean contains(String nameNode) {
        for(Node node : nodes){
            if (node.getName().equals(nameNode)){
                return true;
            }
        }
        return false;
    }

    public Node getNode(String nameNode) {
        for(Node node : nodes){
            if (node.getName().equals(nameNode)){
                return node;
            }
        }
        return null;
    }

    public void addNode(Node nodeA) {
        if(!contains(nodeA.getName())) {
            nodes.add(nodeA);
        }
    }

    public void resetNodes(){
        for (Node node:nodes) {
            node.setDistance(Integer.MAX_VALUE);
            node.getShortestPath().clear();
        }
    }
}
