package pl.edu.pw.ee.calculations;

import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Calculations {
    public Calculations(String input) throws Exception {
        if(!validateInput(input)) {
            throw new Exception(); //TODO: implement exception
        }
        //TODO: implement
    }

    private Stack<String> shuntingYardParser(String input) {
        StringTokenizer tokens = new StringTokenizer(input, "+-×÷()^", true);
        Stack<String> operatorStack = new Stack<String>();
        Stack<String> outputStack = new Stack<String>(); 

        Map<String, Integer> operatorPrecedence = new TreeMap<>(); 
        operatorPrecedence.put("^", 4);
        operatorPrecedence.put("×", 3);
        operatorPrecedence.put("÷", 3);
        operatorPrecedence.put("+", 2);
        operatorPrecedence.put("-", 2);

        while(tokens.hasMoreTokens()) {
            String token = tokens.nextToken();

            if(isNumber(token)) {
                outputStack.push(token); 
            } else {
                if(token.equals("(")) {
                    while(operatorStack.peek().equals(")")) {
                        outputStack.push(operatorStack.pop()); 
                    }
                } else if(token.equals(")")) {
                    operatorStack.push(token);
                } else if(operatorStack.empty() || operatorStack.peek().equals("(") || operatorPrecedence.get(operatorStack.peek()) < operatorPrecedence.get(token)) {
                    operatorStack.push(token);
                } else {
                    outputStack.push(operatorStack.pop()); 
                    operatorStack.push(token); 
                }
            }
        }

        while(!operatorStack.empty()) {
            outputStack.push(operatorStack.pop());
        }

        return outputStack;
    }

    private ComplexNum calculate(Stack<String> rpn) {
        rpn = reverseStack(rpn);

        return null; //TODO: implement
    }

    public String getAnswer() {
        return null; //TODO: implement
    }

    private boolean isNumber(String str) {
        return str.matches("[0-9.i]+");
    }

    private static Stack<String> reverseStack(Stack<String> stack) {
        Stack<String> reversed = new Stack<String>(); 
        while(!stack.empty()) {
            reversed.push(stack.pop()); 
        }
        return reversed;
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
