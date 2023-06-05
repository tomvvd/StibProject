package g58594.atlg4.stibRide.presenter;

import g58594.atlg4.stibRide.model.Stib;
import g58594.atlg4.stibRide.model.dto.FavoriDto;
import g58594.atlg4.stibRide.model.graph.Node;
import g58594.atlg4.stibRide.model.repository.FavoriRepository;
import g58594.atlg4.stibRide.model.repository.RepositoryException;
import g58594.atlg4.stibRide.util.Observer;
import g58594.atlg4.stibRide.view.View;

import java.util.List;

public class Presenter implements Observer {
    private Stib stib;
    private View view;

    public Presenter(Stib stib, View view) {
        this.stib = stib;
        this.view = view;
    }

    public void initializeSearchBox() {
        view.setUpStation(stib.getAllStationsName());
        view.setUpFavori(stib.getAllFavorisName());
    }

    public void searchShortestPath(){
        String origin = view.getOrigin();
        String dest = view.getDestination();
        if(origin==null || dest==null){
            view.errorSearch();
        }else {
            stib.searchShortestPath(origin, dest);
        }
    }

    @Override
    public void update(Object object) {
        Node dest = (Node) object;
        view.displayPath(dest);
    }

    public void loadOriginDest() {
        String favori = view.getFavori();
        FavoriRepository favoriRepository = new FavoriRepository();
        try {
            FavoriDto favoriDto = favoriRepository.get(favori);
            view.changeValueOriginDest(favoriDto.getOrigin(),favoriDto.getDestination());
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyFavori() {
        String favori = view.getFavori();
        String newOrigin = view.getOrigin();
        String newDest = view.getDestination();
        FavoriRepository favoriRepository = new FavoriRepository();
        try {
            favoriRepository.modify(favori,newOrigin,newDest);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFavori() {
        String favori = view.getFavori();
        FavoriRepository favoriRepository = new FavoriRepository();
        try {
            favoriRepository.delete(favori);
            view.setUpFavori(stib.getAllFavorisName());
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addFavori() {
        String favori = view.getNewNameFav();
        String origin = view.getOrigin();
        String dest = view.getDestination();
        FavoriRepository favoriRepository = new FavoriRepository();
        try {
            if(favoriRepository.contains(favori) || favori.equals("")){
                view.errorMessage();
            }
            else {
                favoriRepository.add(favori, origin, dest);
                view.setUpFavori(stib.getAllFavorisName());
            }
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }
}
