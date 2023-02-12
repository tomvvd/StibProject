module g58594.atlg4.studenttable {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens g58594.atlg4.studenttable to javafx.fxml;
    exports g58594.atlg4.studenttable;
}