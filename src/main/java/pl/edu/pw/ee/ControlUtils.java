package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ControlUtils {
    public static int lastTextId(TextFlow txtFlow) {
        return txtFlow.getChildren().size() - 1;
    }

    public static String lastSign(TextFlow txtFlow) {
        return ((Text)(txtFlow.getChildren().get(lastTextId(txtFlow)))).getText();
    }

    public static void buttonInput(ActionEvent event, TextFlow txtFlow) {
        Button buttonClicked = (Button) event.getSource(); 
        Text text = new Text(buttonClicked.getText()); 
        text.setFont(Font.font("Helvetica", 32));
        txtFlow.getChildren().add(text); 
        txtFlow.layout();
    }
}
