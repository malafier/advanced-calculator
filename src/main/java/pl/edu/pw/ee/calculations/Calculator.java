package pl.edu.pw.ee.calculations;

import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

    public static String getAnswer(String input) throws NumberFormatException, IllegalArgumentException, ArithmeticException {
        input = parenthisise(input)
            .replace("(-", "(0-")
            .replace("×", "*")
            .replace("÷", "/")
            .replace("𝑖", "i");

        Stack<String> reversePolish = shuntingYardParser(input);
        return calculate(reversePolish).toString();
    }

    private static Stack<String> shuntingYardParser(String input) {
        StringTokenizer tokens = new StringTokenizer(input, "+-*/()^", true);
        Stack<String> operatorStack = new Stack<String>(), 
                      outputStack = new Stack<String>(); 

        Map<String, Integer> operatorPrecedence = Map.of(
            "^", 4,
            "*", 3,
            "/", 3,
            "+", 2,
            "-", 2
        );

        while(tokens.hasMoreTokens()) {
            String token = tokens.nextToken();

            if(isNumber(token)) {
                outputStack.push(token); 
            } else {
                if(token.equals(")")) {
                    while(!operatorStack.peek().equals("(")) {
                        outputStack.push(operatorStack.pop()); 
                    }
                    operatorStack.pop();
                } else if(token.equals("(")) {
                    operatorStack.push(token);
                } else if(operatorStack.empty() || operatorStack.peek().equals("(") || 
                        operatorPrecedence.get(operatorStack.peek()) < operatorPrecedence.get(token)) {
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

    private static ComplexNumber calculate(Stack<String> rpn) throws ArithmeticException, IllegalArgumentException {
        rpn = reverseStack(rpn);
        Stack<ComplexNumber> helpingStack = new Stack<>(); 

        while(!rpn.empty()) {
            String popped = rpn.pop(); 
            if(isNumber(popped)) {
                helpingStack.push(new ComplexNumber(popped)); 
            } else {
                ComplexNumber second = helpingStack.pop(); 
                ComplexNumber first = helpingStack.empty() ? new ComplexNumber(0, 0) : helpingStack.pop(); 
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

    private static String parenthisise(String input) {
        int leftPar = (int) input.chars().filter(c -> c == '(').count();
        int rightPar = (int) input.chars().filter(c -> c == ')').count();

        if(rightPar > leftPar) {
            throw new IllegalArgumentException("Too mant right parenthisis.");
        } 

        if(rightPar < leftPar) {
            StringBuilder sb = new StringBuilder(input); 
            for(int i=0; i < (leftPar - rightPar); i++) {
                sb.append(')');
            }
            input = sb.toString();
        }

        return input;
    }
}
