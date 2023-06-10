package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ControlUtils {
    private static final int MAX_SIGNS_IN_LINE = 20; 

    public static int lastTextId(TextFlow txtFlow) {
        return txtFlow.getChildren().size() - 1;
    }

    public static String lastSign(TextFlow txtFlow) {
        return ((Text)(txtFlow.getChildren().get(lastTextId(txtFlow)))).getText();
    }

    public static void buttonInput(ActionEvent event, TextFlow txtFlow) {
        if(!hasLessThan20Signs(txtFlow)) {
            return;
        }

        Button buttonClicked = (Button) event.getSource(); 
        Text text = new Text(buttonClicked.getText()); 

        if(lastTextId(txtFlow) >= 0 && text.getText().equals("0")) {
            if(illegalZero(txtFlow)) {
                return;
            }
        }

        text.setFont(Font.font("Helvetica", 32));
        txtFlow.getChildren().add(text); 
        txtFlow.layout();
    }

    private static boolean hasLessThan20Signs(TextFlow txtFlow) {
        StringBuilder equasion = new StringBuilder(); 
        for(int i=0; i < txtFlow.getChildren().size(); i++) {
            equasion.append(((Text)(txtFlow.getChildren().get(i))).getText());
        }
        return equasion.toString().length() < MAX_SIGNS_IN_LINE;
    }

    public static boolean hasLessThan17Signs(TextFlow txtFlow) {
        StringBuilder equasion = new StringBuilder(); 
        for(int i=0; i < txtFlow.getChildren().size(); i++) {
            equasion.append(((Text)(txtFlow.getChildren().get(i))).getText());
        }
        return equasion.toString().length() < MAX_SIGNS_IN_LINE - 3;
    }

    private static boolean illegalZero(TextFlow txtFlow) {
        final String endoOfNumberSign = "+-×÷^(", legalNeighbour = "123456789.";

        int i = ControlUtils.lastTextId(txtFlow); 
        String signToChceck = ((Text)(txtFlow.getChildren().get(i))).getText(); 
        if(signToChceck.equals(".") || endoOfNumberSign.contains(signToChceck)) {
            return false;
        }

        while(i >= 0 && !endoOfNumberSign.contains(signToChceck)) {
            signToChceck = ((Text)(txtFlow.getChildren().get(i))).getText(); 
            if(legalNeighbour.contains(signToChceck)) {
                return false;
            }
            i--;
        }

        return true;
    }
}
