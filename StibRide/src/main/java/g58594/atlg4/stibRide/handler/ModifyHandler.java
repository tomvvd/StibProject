package g58594.atlg4.stibRide.handler;

import g58594.atlg4.stibRide.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ModifyHandler implements EventHandler<ActionEvent> {
    private Presenter presenter;

    public ModifyHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent event) {
        presenter.modifyFavori();
    }
}
