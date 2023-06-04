package g58594.atlg4.stibRide.model;

import g58594.atlg4.stibRide.model.dto.FavoriDto;
import g58594.atlg4.stibRide.model.dto.StopDto;
import g58594.atlg4.stibRide.model.graph.AlgoDijkstra;
import g58594.atlg4.stibRide.model.graph.Graph;
import g58594.atlg4.stibRide.model.graph.Node;
import g58594.atlg4.stibRide.model.repository.FavoriRepository;
import g58594.atlg4.stibRide.model.repository.RepositoryException;
import g58594.atlg4.stibRide.model.repository.StopRepository;
import g58594.atlg4.stibRide.util.Observable;
import g58594.atlg4.stibRide.util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Stib implements Observable {

    private List<Observer> observers;
    private Graph graph;
    private List<String> favoriName;

    public Stib() {
        this.observers = new ArrayList<>();
        graph = new Graph();
        favoriName = getAllFavorisName();
        try {
            ConfigManager.getInstance().load();
            constructionGraph();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void constructionGraph() throws RepositoryException {
        StopRepository stopRepository = new StopRepository();
        //stopDto --> getKey().getFirst() = id_line & getKey().getSecond() = id_station
        List<StopDto> list = stopRepository.getAll();

        Node node = new Node(list.get(0).getNameStation(), list.get(0).getKey().getFirst());
        for (int i = 1; i < list.size(); i++) {
            StopDto stopDto = list.get(i);
            Node nextNode;
            if(graph.contains(stopDto.getNameStation())){
                nextNode = graph.getNode(stopDto.getNameStation());
                nextNode.addLine(stopDto.getKey().getFirst());
            }else{
                nextNode = new Node(stopDto.getNameStation(),stopDto.getKey().getFirst());
            }
            if(list.get(i-1).getKey().getFirst()==stopDto.getKey().getFirst()) {
                node.addDestination(nextNode, 1);
                nextNode.addDestination(node, 1);
            }
            graph.addNode(node);
            node = nextNode;
        }
        graph.addNode(node);
    }

    public void searchShortestPath(String origin, String dest){
        Node originNode = graph.getNode(origin);
        graph = AlgoDijkstra.calculateShortestPathFromSource(graph,originNode);
        Node destNode = graph.getNode(dest);
        notifyObserver(destNode);
    }

    public List<String> getAllStationsName() {
        List<String> names = new ArrayList<>();
        try {
            StopRepository stopRepository = new StopRepository();
            List<StopDto> list = stopRepository.getAll();
            for (StopDto station : list) {
                if(!names.contains(station.getNameStation())) {
                    names.add(station.getNameStation());
                }
            }
        } catch (RepositoryException ex) {
            System.out.println(ex.getMessage());
        }
        return names;
    }

    private List<String> getAllFavorisName() {
        List<String> favoris = new ArrayList<>();
        try {
            FavoriRepository favoriRepository = new FavoriRepository();
            List<FavoriDto> list = favoriRepository.getAll();
            for (FavoriDto favori : list) {
                favoris.add(favori.getKey());
            }
        } catch (RepositoryException ex) {
            System.out.println(ex.getMessage());
        }
        return favoris;
    }

    public List<String> getFavoriName() {
        return favoriName;
    }

    @Override
    public void register(Observer observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Object obj) {
        for (Observer o:observers) {
            o.update(obj);
        }
    }
}
