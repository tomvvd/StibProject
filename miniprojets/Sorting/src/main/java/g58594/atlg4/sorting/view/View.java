package g58594.atlg4.sorting.view;
import g58594.atlg4.sorting.controller.Controller;
import g58594.atlg4.sorting.handler.SortHandler;
import g58594.atlg4.sorting.model.Difficulty;
import g58594.atlg4.sorting.model.Model;
import g58594.atlg4.sorting.model.SortRecord;
import g58594.atlg4.sorting.model.SortType;
import g58594.atlg4.sorting.util.Observer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class View implements Observer {
    private ObservableList<XYChart.Data<Integer,Long>> bubbleData; //ensemble de mes points (=Data) sur le graphe
    private ObservableList<XYChart.Data<Integer,Long>> mergeData;
    @FXML
    private Spinner<Integer> threadSpinner;
    @FXML
    private ChoiceBox<SortType> sortChoice;
    @FXML
    private ChoiceBox<Difficulty> configurationChoice;
    @FXML
    private TableView<SortRecord> table;
    @FXML
    private TableColumn<SortRecord,SortType> nameCol;
    @FXML
    private TableColumn<SortRecord,Integer> sizeCol;
    @FXML
    private TableColumn<SortRecord,Long> swapCol;
    @FXML
    private TableColumn<SortRecord,Long> durationCol;
    @FXML
    private LineChart<Integer,Long> chart;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label leftStatus;
    @FXML
    private Button start;

    public View() {
    }

    public SortType getSortType(){
        return this.sortChoice.getValue();
    }

    public Difficulty getDifficulty(){
        return this.configurationChoice.getValue();
    }

    public int getNbThreads(){
        return this.threadSpinner.getValue();
    }

    public void addHandlerButton(Controller controller) {
        SortHandler handler = new SortHandler(controller);
        start.setOnAction(handler);
    }

    private void initializeSpinner() {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, Integer.MAX_VALUE, 2);
        this.threadSpinner.setValueFactory(valueFactory);
    }
    private void initializeSortChoice() {
        sortChoice.getItems().addAll(SortType.values());
        sortChoice.getSelectionModel().select(0);
    }
    private void initializeConfigurationChoice(){
        configurationChoice.getItems().addAll(Difficulty.values());
        configurationChoice.getSelectionModel().select(0);
    }
    private void initializeTable(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("sortType"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(new PropertyValueFactory<>("nbOp"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }
    private void initializeChart(){
        bubbleData = FXCollections.observableArrayList();
        mergeData = FXCollections.observableArrayList();

        ObservableList<XYChart.Series<Integer,Long>> series = FXCollections.observableArrayList();

        series.add(new XYChart.Series<Integer,Long>("Bubble sort", bubbleData)); //forme la courbe = series
        series.add(new XYChart.Series<Integer,Long>("Merge sort", mergeData));

        chart.getData().setAll(series);
    }
    public void initialize() {
        initializeSpinner();
        initializeSortChoice();
        initializeConfigurationChoice();
        initializeChart();
        initializeTable();
    }

    private void addChartRecord(SortRecord sortRecord){
        var data = new XYChart.Data<Integer, Long>(sortRecord.getSize(),sortRecord.getNbOp());
        switch(sortRecord.getSortType()){
            case BUBBLESORT -> bubbleData.add(data);
            case MERGESORT -> mergeData.add(data);
        }
    }

    public void resetProgressBar() {
        this.progressBar.setProgress(0);
    }

    private void updateProgressBar() {
        double newProgress = this.progressBar.getProgress() + 1.0/10;
        this.progressBar.setProgress(newProgress);
    }

    private void numberActiveThreads() {
        this.leftStatus.setText("Active thread : " + Thread.activeCount());
    }

    @Override
    public void update(Object object) {
        Platform.runLater(() -> {
            SortRecord sortRecord = (SortRecord) object;
            table.getItems().add(sortRecord);
            addChartRecord(sortRecord);
            updateProgressBar();
            numberActiveThreads();
        });
    }
}