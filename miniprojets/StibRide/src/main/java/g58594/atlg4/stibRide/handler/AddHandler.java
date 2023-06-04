package g58594.atlg4.stibRide.handler;

import g58594.atlg4.stibRide.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddHandler implements EventHandler<ActionEvent> {
    private Presenter presenter;

    public AddHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent event) {
        presenter.addFavori();
    }
}
