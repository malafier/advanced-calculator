package pl.edu.pw.ee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    //

    @FXML
    private void clickTextBtn(ActionEvent event) {
        Button buttonClicked = (Button) event.getSource(); 
        System.out.println(buttonClicked.getText());
    }
}
