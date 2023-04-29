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

    @FXML
    private void clickNumberBtn(ActionEvent event) {
        final String invalidNeighbour = ")𝑖";
        if(ControlUtils.lastTextId(txtFlow) >= 0) {
            if(invalidNeighbour.contains(ControlUtils.lastSign(txtFlow))) {
                return;
            }
        }
        ControlUtils.buttonInput(event, txtFlow);
    }

    @FXML
    private void clickDelBtn() {
        if(ControlUtils.lastTextId(txtFlow) >= 0) {
            txtFlow.getChildren().remove(ControlUtils.lastTextId(txtFlow)); 
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
    private void clickOperandBtn(ActionEvent event) {//TODO: Ans
        final String invalidNeighbour = "+-×÷^(.";
        if(ControlUtils.lastTextId(txtFlow) < 0) {
            return;
        }
        boolean minusCanBeAfterLeftPar = !(((Button)event.getSource()).getText().equals("-") && ControlUtils.lastSign(txtFlow).equals("("));
        if(invalidNeighbour.contains(ControlUtils.lastSign(txtFlow)) && minusCanBeAfterLeftPar) {
            return; 
        }
        ControlUtils.buttonInput(event, txtFlow);
    }

    @FXML
    private void clickDotBtn(ActionEvent event) {
        final String invalidNeighbour = "+-×÷^()𝑖.", endoOfNumberSign = "+-×÷^(";
        boolean hasDotFlag = false;

        if(ControlUtils.lastTextId(txtFlow) < 0 || invalidNeighbour.contains(ControlUtils.lastSign(txtFlow))) {
            return;
        }

        int i = ControlUtils.lastTextId(txtFlow); 
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

        ControlUtils.buttonInput(event, txtFlow);
    }

    @FXML
    private void clickImgBtn(ActionEvent event) {
        final String invalidNeighbour = "^)."; 
        if(ControlUtils.lastTextId(txtFlow) >= 0) {
            if(invalidNeighbour.contains(ControlUtils.lastSign(txtFlow))) {
                return;
            }
        }
        ControlUtils.buttonInput(event, txtFlow);
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
    private void clickLeftParenthisisBtn(ActionEvent event) {//FIXME: check if everything id ok
        if(ControlUtils.lastTextId(txtFlow) < 0) {
            ControlUtils.buttonInput(event, txtFlow);
            return;
        }

        final String validNeighbour = "^+-(.×÷"; 
        if(validNeighbour.contains(ControlUtils.lastSign(txtFlow))) {
            ControlUtils.buttonInput(event, txtFlow);
        }
    }

    @FXML
    private void clickRightParenthisisBtn(ActionEvent event) {
        final String invalidNeighbour = "^+-(.×÷"; 
        if(ControlUtils.lastTextId(txtFlow) < 1 || invalidNeighbour.contains(ControlUtils.lastSign(txtFlow))) {
            return;
        }
        
        ControlUtils.buttonInput(event, txtFlow);
    }
}
