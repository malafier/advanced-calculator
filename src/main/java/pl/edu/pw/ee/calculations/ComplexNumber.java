package pl.edu.pw.ee.calculations;

import java.text.DecimalFormat;

public class ComplexNumber {
    private double real; 
    private double imaginary; 

    private static final int MAX_DECIMAL_INPUT = 10;

    public ComplexNumber(double real, double imaginary) {
        this.real = real; 
        this.imaginary = imaginary; 
    }

    public ComplexNumber(String s) throws NumberFormatException {
        String[] parts = s.replace(',', '.')
            .split("(?=[+-])"); // split string at + or -
    
        if (parts.length == 1) { // only real or imaginary part
            parseSinglePart(parts[0]);
        } else if (parts.length == 2) { // real and imaginary part
            parseRealAndImaginary(parts[0], parts[1]);
        } 
    }
    
    private void parseSinglePart(String s) throws NumberFormatException {
        if (s.startsWith("0") && !s.equals("0")) {
            s = s.substring(1); // Remove the leading zero
        }

        if(isOutOfRange(s)) {
            throw new NumberFormatException();
        }

        if (s.endsWith("i")) { // imaginary 
            real = 0;
            imaginary = parseImaginary(s);
        } else { // real 
            real = Double.parseDouble(s);
            imaginary = 0;
        }
    }

    private boolean isOutOfRange(String s) {
        return s.indexOf('.') >= 0 && s.substring(s.indexOf('.')+1, s.length()).length() > MAX_DECIMAL_INPUT;
    }
    
    private void parseRealAndImaginary(String realPart, String imaginaryPart) throws NumberFormatException {
        real = Double.parseDouble(realPart);
        imaginary = parseImaginary(imaginaryPart);
    }

    private double parseImaginary(String s) {
        if(s.length() == 1) {
            return 1;
        }
        return Double.parseDouble(s.substring(0, s.length() - 1));
    }

    public ComplexNumber performOperation(ComplexNumber other, String operator) throws ArithmeticException {
        switch (operator) {
            case "+": return this.add(other);
            case "-": return this.substract(other);
            case "*": return this.multiply(other);
            case "/": return this.divide(other);
            case "^": return this.powerOf(other);
            default: throw new ArithmeticException();
        }
    }

    public ComplexNumber add(ComplexNumber other) {
        double resultReal = this.real + other.real; 
        double resultImaginary = this.imaginary + other.imaginary; 

        return new ComplexNumber(resultReal, resultImaginary); 
    } 

    public ComplexNumber substract(ComplexNumber other) {
        double resultReal = this.real - other.real; 
        double resultImaginary = this.imaginary - other.imaginary; 

        return new ComplexNumber(resultReal, resultImaginary); 
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double resultReal = this.real * other.real - this.imaginary * other.imaginary; 
        double resultImaginary = this.real * other.imaginary + other.real * this.imaginary; 

        return new ComplexNumber(resultReal, resultImaginary); 
    }

    public ComplexNumber divide(ComplexNumber other) throws ArithmeticException {
        if(other.real == 0 && other.imaginary == 0) {
            throw new ArithmeticException("Division by zero!"); 
        }

        double otherLength = other.real * other.real + other.imaginary * other.imaginary; 

        double resultReal = (this.real * other.real + this.imaginary * other.imaginary) / otherLength; 
        double resultImaginary = (other.real * this.imaginary - this.real * other.imaginary) / otherLength; 

        return new ComplexNumber(resultReal, resultImaginary); 
    }

    public ComplexNumber powerOf(ComplexNumber other) throws ArithmeticException {
        if(other.imaginary != 0) {
            throw new ArithmeticException();
        }

        if(other.real % 1 != 0) {
            if(this.imaginary != 0 ) {
                throw new ArithmeticException();
            }

            return new ComplexNumber(Math.pow(this.real, other.real), 0);
        }

        ComplexNumber answer = new ComplexNumber(1, 0); 
        ComplexNumber factor = other.real >= 0 ? this : new ComplexNumber(1, 0).divide(this);
        for(int i=1; i <= Math.abs(other.real); i++) {
            answer = answer.multiply(factor); 
        }

        return answer;
    }

    @Override
    public String toString() {
        if (real == 0 && imaginary == 0) {
            return "0";
        }
    
        DecimalFormat decimalFormat = new DecimalFormat("#.##########"); 
    
        StringBuilder sb = new StringBuilder();
        if (real != 0) {
            String formattedReal = decimalFormat.format(real);
            if (formattedReal.equals("-0")) {
                formattedReal = "0"; 
            }
            sb.append(formattedReal);
        }
    
        if (imaginary != 0) {
            if (sb.length() > 0) {
                sb.append(imaginary < 0 ? "-" : "+");
            } else if(sb.length() == 0 && imaginary < 0) {
                sb.append("-");
            }
            double absImaginary = Math.abs(imaginary);
            if (absImaginary % 1 == 0) {
                if (absImaginary != 1) {
                    sb.append((int) absImaginary);
                }
            } else {
                String formattedImaginary = decimalFormat.format(absImaginary);
                if (formattedImaginary.equals("-0")) {
                    formattedImaginary = "0"; 
                }
                sb.append(formattedImaginary);
            }
            sb.append("i");
        }
    
        return sb.toString();
    }

    public String toFormatedString() {
        if(real == 0 && imaginary == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        if (real != 0) {
            sb.append(outputFormat(real));
        }

        if (imaginary != 0) {
            if (sb.length() > 0) {
                sb.append(imaginary < 0 ? "-" : "+");
            }
            double absImaginary = Math.abs(imaginary);
            sb.append(outputFormat(absImaginary) + "i");
        }

        return sb.toString();
    }

    private String outputFormat(double value) {
        if(value >= 10000) { //val too high
            int e = 0;
            while(value >= 10) {
                value /= 10;
                e++;
            }
            double roundedValue = Math.round(value * 10000) / 10000.0; 
            return Double.toString(roundedValue) + "e" + Integer.toString(e);
        } 

        if(value < 0.0001) { //val too small
            int e = 0;
            while(value < 1) {
                value *= 10;
                e--;
            }
            double roundedValue = Math.round(value * 10000) / 10000.0; 
            return Double.toString(roundedValue) + "e" + Integer.toString(e);
        }

        double roundedValue = Math.round(value * 10000) / 10000.0;
        if(Math.abs(roundedValue) % 1.0 == 0.0) {
            return Integer.toString((int) roundedValue);
        }
        return Double.toString(roundedValue);
    }
}
