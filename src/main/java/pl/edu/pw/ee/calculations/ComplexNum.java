package pl.edu.pw.ee.calculations;

public class ComplexNum {
    private double real; 
    private double imaginary; 

    public ComplexNum(double real, double imaginary) {
        this.real = real; 
        this.imaginary = imaginary; 
    }

    public ComplexNum(String s) {
        String[] parts = s.split("(?=[+-])"); // split string at + or -
    
        if (parts.length == 1) { // only real or imaginary part
            parseSinglePart(parts[0]);
        } else if (parts.length == 2) { // real and imaginary part
            parseRealAndImaginary(parts[0], parts[1]);
        } 
    }
    
    private void parseSinglePart(String s) {
        if (s.endsWith("i")) { // imaginary 
            real = 0;
            imaginary = Double.parseDouble(s.substring(0, s.length() - 1));
        } else { // real 
            real = Double.parseDouble(s);
            imaginary = 0;
        }
    }
    
    private void parseRealAndImaginary(String realPart, String imaginaryPart) {
        real = Double.parseDouble(realPart);
        imaginary = Double.parseDouble(imaginaryPart.substring(0, imaginaryPart.length() - 1));
    }

    public ComplexNum performOperation(ComplexNum other, String operator) throws ArithmeticException {
        switch (operator) {
            case "+": return this.add(other);
            case "-": return this.substract(other);
            case "*": return this.multiply(other);
            case "/": return this.divide(other);
            case "^": return this.powerOf(other);
            default: throw new ArithmeticException();
        }
    }

    public ComplexNum add(ComplexNum other) {
        double resultReal = this.real + other.real; 
        double resultImaginary = this.imaginary + other.imaginary; 

        return new ComplexNum(resultReal, resultImaginary); 
    } 

    public ComplexNum substract(ComplexNum other) {
        double resultReal = this.real - other.real; 
        double resultImaginary = this.imaginary - other.imaginary; 

        return new ComplexNum(resultReal, resultImaginary); 
    }

    public ComplexNum multiply(ComplexNum other) {
        double resultReal = this.real * other.real + this.imaginary * other.imaginary; 
        double resultImaginary = this.real * other.imaginary + other.real * this.imaginary; 

        return new ComplexNum(resultReal, resultImaginary); 
    }

    public ComplexNum divide(ComplexNum other) throws ArithmeticException {
        if(other.real == 0 && other.imaginary == 0) {
            throw new ArithmeticException("Division by zero!"); 
        }

        double otherLengthSquared = other.real * other.real + other.imaginary * other.imaginary; 

        double resultReal = (this.real * other.real + this.imaginary * other.imaginary) / otherLengthSquared; 
        double resultImaginary = (other.real * this.imaginary - this.real * other.imaginary) / otherLengthSquared; 

        return new ComplexNum(resultReal, resultImaginary); 
    }

    public ComplexNum powerOf(ComplexNum other) throws ArithmeticException {
        if(other.imaginary != 0 || other.real % 1 != 0) {//add rooting real numbers
            throw new ArithmeticException();
        }

        ComplexNum answer = new ComplexNum(1, 0); 
        for(int i=1; i <= other.real; i++) {
            answer = answer.multiply(this); 
        }

        return answer;
    }

    @Override
    public String toString() {
        if(real == 0 && imaginary == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        if (real != 0) {
            if (real % 1 == 0) {
                sb.append((int) real);
            } else {
                sb.append(real);
            }
        }

        if (imaginary != 0) {
            if (sb.length() > 0) {
                sb.append(imaginary < 0 ? " - " : " + ");
            }
            double absImaginary = Math.abs(imaginary);
            if (absImaginary % 1 == 0) {
                sb.append((int) absImaginary);
            } else {
                sb.append(absImaginary);
            }
            sb.append("i");
        }

        return sb.toString();
    }
}
