package pl.edu.pw.ee.calculations;

public class ComplexNum {
    private double real; 
    private double imaginary; 

    public ComplexNum(double real, double imaginary) {
        this.real = real; 
        this.imaginary = imaginary; 
    }

    public ComplexNum(String s) {
        if(s.contains("i")) {
            real = Double.parseDouble(s); 
            imaginary = 0;
        } else {
            real = 0;
            imaginary = Double.parseDouble(s.substring(0, s.lastIndexOf(s)-1));
        }
    }

    public ComplexNum performOperation(ComplexNum other, String operator) throws ArithmeticException {
        switch (operator) {
            case "+":
                return this.add(other);
            case "-":
                return this.substract(other);
            case "×":
                return this.multiply(other);
            case "÷":
                return this.divide(other);
            case "^":
                return this.powerOf(other);
            default:
                throw new ArithmeticException();
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
            throw new ArithmeticException("You can't divide by ZERO!"); 
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
        for(int i=1; i < other.real; i++) {
            answer.multiply(this); 
        }

        return answer;
    }

    @Override
    public String toString() {
        return real + "+" + imaginary + "i"; 
    }
}
