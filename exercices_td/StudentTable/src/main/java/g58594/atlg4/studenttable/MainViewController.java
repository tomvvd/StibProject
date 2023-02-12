package g58594.atlg4.studenttable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student,Integer> colonneNum;
    @FXML
    private TableColumn<Student,String> colonnePrenom;
    @FXML
    private TableColumn<Student,String> colonneNom;
    @FXML
    private TextField numTF;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField nomTF;

    @FXML
    private void handleButtonAction(ActionEvent event){
        String num = numTF.getText();
        String prenom = prenomTF.getText();
        String nom = nomTF.getText();
        if(!num.equals("") && !prenom.equals("") && !nom.equals("")) {
            Student std = new Student(Integer.parseInt(num), prenom, nom);
            table.getItems().add(std);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colonneNum.setCellValueFactory(new PropertyValueFactory<Student,Integer>("numero"));
        colonnePrenom.setCellValueFactory(new PropertyValueFactory<Student,String>("prenom"));
        colonneNom.setCellValueFactory(new PropertyValueFactory<Student,String>("nom"));
    }
}
