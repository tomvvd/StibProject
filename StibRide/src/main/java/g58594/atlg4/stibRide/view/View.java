package g58594.atlg4.stibRide.view;

import g58594.atlg4.stibRide.handler.*;
import g58594.atlg4.stibRide.model.graph.Node;
import g58594.atlg4.stibRide.presenter.Presenter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.controlsfx.control.SearchableComboBox;

import java.util.List;

public class View {
    @FXML
    private Button search;
    @FXML
    private SearchableComboBox<String> origin;
    @FXML
    private SearchableComboBox<String> destination;
    @FXML
    private TableView<Node> table;
    @FXML
    private TableColumn<Node,String> stations;
    @FXML
    private TableColumn<Node,String> lignes;
    @FXML
    private SearchableComboBox<String> favori;
    @FXML
    private TextField tfFav;
    @FXML
    private Button loadButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button modifyButton;

    public View() {}

    //appel lors du chargement
    public void initialize(){
        setUpTableView();
    }

    public String getOrigin(){
        return this.origin.getValue();
    }

    public String getDestination(){
        return this.destination.getValue();
    }

    public String getFavori(){
        return this.favori.getValue();
    }

    public String getNewNameFav(){
        return this.tfFav.getText();
    }

    public void addHandlerSearchButton(Presenter presenter) {
        SearchHandler handler = new SearchHandler(presenter);
        this.search.setOnAction(handler);
    }

    public void addHandlerLoadButton(Presenter presenter) {
        LoadHandler handler = new LoadHandler(presenter);
        this.loadButton.setOnAction(handler);
    }

    public void addHandlerAddButton(Presenter presenter) {
        AddHandler handler = new AddHandler(presenter);
        this.addButton.setOnAction(handler);
    }

    public void addHandlerDeleteButton(Presenter presenter) {
        DeleteHandler handler = new DeleteHandler(presenter);
        this.deleteButton.setOnAction(handler);
    }

    public void addHandlerModifyButton(Presenter presenter) {
        ModifyHandler handler = new ModifyHandler(presenter);
        this.modifyButton.setOnAction(handler);
    }

    public void setUpStation(List<String> stationName) {
        ObservableList<String> stations = FXCollections.observableArrayList(stationName);
        this.origin.setItems(stations);
        this.destination.setItems(stations);
    }

    private void setUpTableView() {
        this.stations.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.lignes.setCellValueFactory(new PropertyValueFactory<>("Lines"));
    }

    public void setUpFavori(List<String> favoriName){
        ObservableList<String> favoris = FXCollections.observableArrayList(favoriName);
        this.favori.setItems(favoris);
    }

    public void changeValueOriginDest(String origin, String dest){
        this.origin.setValue(origin);
        this.destination.setValue(dest);
    }

    public void displayPath(Node destination) {
        table.getItems().clear();
        List<Node> path = destination.getShortestPath();
        for(Node n : path) {
            table.getItems().add(n);
        }
        table.getItems().add(destination);
    }

    public void errorMessage() {
        Label label = new Label("Soit vous avez oublié d'entrer un nom pour votre nouveau favori, soit ce nom existe déjà !");
        Pane pane = new Pane(label);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.getDialogPane().setContent(pane);
        alert.showAndWait();
    }

    public void errorSearch() {
        Label label = new Label("Aucune valeur pour origine ou/et destination !");
        Pane pane = new Pane(label);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.getDialogPane().setContent(pane);
        alert.showAndWait();
    }
}
