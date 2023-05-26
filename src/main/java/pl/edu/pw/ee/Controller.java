package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import pl.edu.pw.ee.calculations.Calculator;
import pl.edu.pw.ee.calculations.ComplexNumber;

public class Controller {
    @FXML 
    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnComma, btnImg;
    
    @FXML
    Button btnEquals, btnPlus, btnMinus, btnTimes, btnDiv, btnPower;

    @FXML
    Button btnDel, btnC, btnAns, btnRightPar, btnLeftPar;

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

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        switch(event.getCode()) {
            //upper bar and numpad
            case DIGIT1:
            case NUMPAD1:
                btnOne.fire();
                break;
            case DIGIT2:
            case NUMPAD2:
                btnTwo.fire();
                break;
            case DIGIT3:
            case NUMPAD3:
                btnThree.fire();
                break;
            case DIGIT4:
            case NUMPAD4:
                btnFour.fire();
                break;
            case DIGIT5:
            case NUMPAD5:
                btnFive.fire();
                break;
            case DIGIT6:
                if(event.isShiftDown()) {
                    btnPower.fire();
                } else {
                    btnSix.fire();
                }
                break;
            case NUMPAD6:
                btnSix.fire();
                break;
            case DIGIT7:
            case NUMPAD7:
                btnSeven.fire();
                break;
            case DIGIT8:
                if(event.isShiftDown()) {
                    btnTimes.fire();
                } else {
                    btnEight.fire();
                }
                break;
            case NUMPAD8:
                btnEight.fire();
                break;
            case DIGIT9:
                if(event.isShiftDown()) {
                    btnLeftPar.fire();
                } else {
                    btnNine.fire();
                }
                break;
            case NUMPAD9:
                btnNine.fire();
                break;
            case DIGIT0:
                if(event.isShiftDown()) {
                    btnRightPar.fire();
                } else {
                    btnZero.fire();
                }
                break;
            case NUMPAD0: 
                btnZero.fire();
                break;
            
            case MINUS:
                btnMinus.fire();
                break;
            case EQUALS:
                if(event.isShiftDown()) {
                    btnPlus.fire();
                } else {
                    btnEquals.fire();
                }
                break;
            
            case DECIMAL:
                btnComma.fire();
                break;
            case ADD:
                btnPlus.fire();
                break;
            case MULTIPLY:
                btnTimes.fire();
                break;
            case DIVIDE:
                btnDiv.fire();
                break;
            case SUBTRACT:
                btnMinus.fire();
                break; 
            case SEPARATOR:
                btnEquals.fire();
                break;

            //other keys
            case I:
                btnImg.fire();
                break;
            case A:
                btnAns.fire();
                break;
            case C:
                btnC.fire();
                break;
            case COMMA:
                btnComma.fire();
                break;
            case SLASH:
                btnDiv.fire();
                break;
            case BACK_SPACE:
                btnDel.fire();
                break;

            default: break;
        }
    }
}
