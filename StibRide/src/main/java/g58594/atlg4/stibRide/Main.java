package g58594.atlg4.stibRide;

import g58594.atlg4.stibRide.model.ConfigManager;
import g58594.atlg4.stibRide.model.Stib;
import g58594.atlg4.stibRide.model.dao.DBManager;
import g58594.atlg4.stibRide.presenter.Presenter;
import g58594.atlg4.stibRide.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX Main
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ConfigManager.getInstance().load();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("stib.fxml"));

        Stib stib = new Stib();
        View view = new View();
        Presenter presenter = new Presenter(stib,view);

        stib.register(presenter);

        fxmlLoader.setController(view);
        VBox root = fxmlLoader.load();

        view.addHandlerSearchButton(presenter);
        view.addHandlerLoadButton(presenter);
        view.addHandlerDeleteButton(presenter);
        view.addHandlerAddButton(presenter);
        view.addHandlerModifyButton(presenter);

        presenter.initializeSearchBox();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}