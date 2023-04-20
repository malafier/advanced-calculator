package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import pl.edu.pw.ee.calculations.Calculations;

public class Controller {
    @FXML
    TextFlow txtFlow;

    @FXML
    private void clickTextBtn(ActionEvent event) {//FIXME: add ')' exception
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

    @FXML
    private void clickClearBtn() {
        txtFlow.getChildren().clear();
        txtFlow.layout();
    }

    @FXML
    private void clickEqualsBtn() {
        StringBuilder equasion = new StringBuilder(); 
        for(int i=0; i < txtFlow.getChildren().size(); i++) {
            equasion.append(((Text)(txtFlow.getChildren().get(i))).getText());
        }
        String result = Calculations.getAnswer(equasion.toString());

        Text text = new Text("\n=" + result); 
        text.setFont(Font.font("Helvetica", 32));
        txtFlow.getChildren().add(text); 
        txtFlow.layout();
    }

    @FXML
    private void clickOperandBtn(ActionEvent event) {
        final String invalidNeighbour = "+-×÷^(.";
        int idOfLastTextNode = txtFlow.getChildren().size() - 1; 
        if(idOfLastTextNode < 0) {
            return;
        }
        String lastSign = ((Text)(txtFlow.getChildren().get(idOfLastTextNode))).getText();
        if(invalidNeighbour.contains(lastSign)) {
            return; 
        }
        clickTextBtn(event);
    }

    @FXML
    private void clickDotBtn(ActionEvent event) {
        final String invalidNeighbour = "+-×÷^().", endoOfNumberSign = "+-×÷^(";
        boolean hasDotFlag = false;
        int idOfLastTextNode = txtFlow.getChildren().size() - 1;

        String neighbour = ((Text)(txtFlow.getChildren().get(idOfLastTextNode))).getText();
        if(idOfLastTextNode < 0 || invalidNeighbour.contains(neighbour)) {
            return;
        }

        int i=idOfLastTextNode; 
        String signToChceck = ((Text)(txtFlow.getChildren().get(i))).getText(); 
        while(i >= 0 && !endoOfNumberSign.contains(signToChceck)) {
            signToChceck = ((Text)(txtFlow.getChildren().get(i))).getText(); 
            if(signToChceck.equals(".")) {
                hasDotFlag = true; 
                break;
            }
            i--;
        }

        if(hasDotFlag) {
            return;
        }

        clickTextBtn(event);
    }
}
