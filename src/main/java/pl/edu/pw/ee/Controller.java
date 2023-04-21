package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import pl.edu.pw.ee.calculations.Calculator;
import pl.edu.pw.ee.calculations.ComplexNumber;

public class Controller {
    @FXML
    TextFlow txtFlow;

    private ComplexNumber memory;

    private void buttonInput(ActionEvent event) {
        Button buttonClicked = (Button) event.getSource(); 
        Text text = new Text(buttonClicked.getText()); 
        text.setFont(Font.font("Helvetica", 32));
        txtFlow.getChildren().add(text); 
        txtFlow.layout();
    }

    @FXML
    private void clickNumberBtn(ActionEvent event) {
        final String invalidNeighbour = ")𝑖";
        int idOfLastTextNode = txtFlow.getChildren().size() - 1;
        if(idOfLastTextNode >= 0) {
            String lastSign = ((Text)(txtFlow.getChildren().get(idOfLastTextNode))).getText();
            if(invalidNeighbour.contains(lastSign)) {
                return;
            }
        }
        buttonInput(event);
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
        if(txtFlow.getChildren().size() - 1 < 0) {
            return;
        }

        StringBuilder equasion = new StringBuilder(); 
        for(int i=0; i < txtFlow.getChildren().size(); i++) {
            equasion.append(((Text)(txtFlow.getChildren().get(i))).getText());
        }
        String result = Calculator.getAnswer(equasion.toString());
        memory = new ComplexNumber(result);

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
        if(invalidNeighbour.contains(lastSign) && !(((Button)event.getSource()).getText().equals("-") && lastSign.equals("("))) {
            return; 
        }
        buttonInput(event);
    }

    @FXML
    private void clickDotBtn(ActionEvent event) {
        final String invalidNeighbour = "+-×÷^()𝑖.", endoOfNumberSign = "+-×÷^(";
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

        buttonInput(event);
    }

    @FXML
    private void clickImgBtn(ActionEvent event) {
        final String invalidNeighbour = "^)."; 
        int idOfLastTextNode = txtFlow.getChildren().size() - 1;
        if(idOfLastTextNode >= 0) {
            String lastSign = ((Text)(txtFlow.getChildren().get(idOfLastTextNode))).getText();
            if(invalidNeighbour.contains(lastSign)) {
                return;
            }
        }
        buttonInput(event);
    }

    @FXML
    private void clickAnswerBtn(ActionEvent event) {
        if(memory == null || memory.toString().equals("0")) {
            return;
        }

        Text text = new Text(memory.toString()); 
        text.setFont(Font.font("Helvetica", 32));
        txtFlow.getChildren().add(text); 
        txtFlow.layout();
    }

    @FXML
    private void clickParenthisisBtn(ActionEvent event) {
        buttonInput(event);
        //TODO: finish
    }
}
