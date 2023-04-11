package pl.edu.pw.ee.calculations;

public class Calculations {
    public Calculations(String input) {
        if(!validateInput(input)) {
            // TODO:implement exception
        }
        //TODO: implement
    }

    public String getAnswer() {
        return null; //TODO: implement
    }

    private boolean validateInput(String input) {
        int leftPar = input.chars().filter(c -> c == '(').sum();
        int rightPar = input.chars().filter(c -> c == ')').sum();

        if(rightPar > leftPar) {
            return false; 
        } else if(rightPar < leftPar) {
            StringBuilder sb = new StringBuilder(input); 
            for(int i=0; i < (leftPar - rightPar); i++) {
                sb.append(')');
            }
            input = sb.toString();
        }

        String operands = "+-×÷."; 
        for(int i=1; i < input.length()-1; i++) {
            if(operands.contains(Character.toString(input.charAt(i))) && (operands.contains(Character.toString(input.charAt(i-1))) || operands.contains(Character.toString(input.charAt(i+1))))) {
                return false; 
            }
        }

        return true;
    }
}
