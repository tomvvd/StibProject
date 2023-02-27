package g58594.atlg4.sorting.view;

import g58594.atlg4.sorting.model.NbElem;
import g58594.atlg4.sorting.model.SortType;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class FxmlController {
    @FXML
    private ChoiceBox<SortType> sortChoice;
    @FXML
     private ChoiceBox<NbElem> configurationChoice;

    public void initialize() {
        sortChoice.getItems().addAll(SortType.values());
        sortChoice.getSelectionModel().select(0);

        configurationChoice.getItems().addAll(NbElem.values());
        configurationChoice.getSelectionModel().select(0);
    }
}