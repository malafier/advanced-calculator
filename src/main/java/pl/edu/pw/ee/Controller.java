package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller {
    @FXML
    TextFlow txtFlow;

    @FXML
    private void clickTextBtn(ActionEvent event) {
        Button buttonClicked = (Button) event.getSource(); 
        Text text = new Text(buttonClicked.getText()); 
        text.setFont(Font.font("Helvetica", 32));
        txtFlow.getChildren().add(text); 
        txtFlow.layout();
    }

    @FXML
    private void clickDelBtn() {
        int idOfLastTextNode = txtFlow.getChildren().size() - 1; 
        if(idOfLastTextNode >= 0) {
            txtFlow.getChildren().remove(idOfLastTextNode); 
        }
    }
}
