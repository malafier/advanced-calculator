package pl.edu.pw.ee.calculations;

import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Calculations {

    public static String getAnswer(String input) throws IllegalArgumentException, ArithmeticException {
        if(!validateInput(input)) {
            throw new IllegalArgumentException();
        }
        Stack<String> reversePolish = shuntingYardParser(input);
        return calculate(reversePolish).toString();
    }

    private static Stack<String> shuntingYardParser(String input) {
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

    private static ComplexNum calculate(Stack<String> rpn) throws ArithmeticException {
        rpn = reverseStack(rpn);
        Stack<ComplexNum> helpingStack = new Stack<>(); 

        while(!rpn.empty()) {
            String popped = rpn.pop(); 
            if(isNumber(popped)) {
                helpingStack.push(new ComplexNum(popped)); 
            } else {
                ComplexNum second = helpingStack.pop(); 
                ComplexNum first = helpingStack.empty() ? new ComplexNum(0, 0) : helpingStack.pop(); 
                helpingStack.push(first.performOperation(second, popped));
            }
        }

        return helpingStack.pop();
    }

    private static boolean isNumber(String str) {
        return str.matches("[0-9.i]+");
    }

    private static Stack<String> reverseStack(Stack<String> stack) {
        Stack<String> reversed = new Stack<String>(); 
        while(!stack.empty()) {
            reversed.push(stack.pop()); 
        }
        return reversed;
    }

    private static boolean validateInput(String input) {
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

        return true;
    }
}
