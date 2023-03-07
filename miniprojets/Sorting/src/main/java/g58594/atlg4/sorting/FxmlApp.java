package g58594.atlg4.sorting;

import g58594.atlg4.sorting.model.Model;
import g58594.atlg4.sorting.view.FxmlController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FxmlApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sort.fxml"));

        Model model = new Model();

        loader.setController(new FxmlController(model));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                model.shutdownExecutor();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
