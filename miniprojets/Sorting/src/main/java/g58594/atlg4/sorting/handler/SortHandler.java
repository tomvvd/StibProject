package g58594.atlg4.sorting.handler;

import g58594.atlg4.sorting.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SortHandler implements EventHandler<ActionEvent> {
    private Controller controller;

    public SortHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        controller.launchSorting();
    }
}
