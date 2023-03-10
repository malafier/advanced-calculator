package pl.edu.pw.ee.calculations;

public class ComplexNum extends Number { // extends Number
    private double real; 
    private double imaginary; 

    public ComplexNum(double real, double imaginary) {
        this.real = real; 
        this.imaginary = imaginary; 
    }

    public ComplexNum(String s) {
        // TODO: zaimplementować, xd
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

    public ComplexNum divide(ComplexNum other) {
        if(other.real == 0 && other.imaginary == 0) {
            throw new IllegalArgumentException("You can't divide by ZERO!"); 
        }

        double otherLengthSquared = other.real * other.real + other.imaginary * other.imaginary; 

        double resultReal = (this.real * other.real + this.imaginary * other.imaginary) / otherLengthSquared; 
        double resultImaginary = (other.real * this.imaginary - this.real * other.imaginary) / otherLengthSquared; 

        return new ComplexNum(resultReal, resultImaginary); 
    }

    @Override
    public String toString() {
        return real + " " + imaginary + "i"; 
    }

    @Override
    public int intValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intValue'");
    }

    @Override
    public long longValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'longValue'");
    }

    @Override
    public float floatValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'floatValue'");
    }

    @Override
    public double doubleValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doubleValue'");
    }
}
