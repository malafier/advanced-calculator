package pl.edu.pw.ee.calculations;

public class ComplexNumber {
    private double real; 
    private double imaginary; 

    public ComplexNumber(double real, double imaginary) {
        this.real = real; 
        this.imaginary = imaginary; 
    }

    public ComplexNumber(String s) {
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
            imaginary = parseImaginary(s);
        } else { // real 
            real = Double.parseDouble(s);
            imaginary = 0;
        }
    }
    
    private void parseRealAndImaginary(String realPart, String imaginaryPart) {
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
        ComplexNumber factor = other.real > 0 ? this : new ComplexNumber(1, 0).divide(this);
        for(int i=1; i <= other.real; i++) {
            answer = answer.multiply(factor); 
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
                sb.append(roundToFourDecimals(real));
            }
        }

        if (imaginary != 0) {
            if (sb.length() > 0) {
                sb.append(imaginary < 0 ? "-" : "+");
            }
            double absImaginary = Math.abs(imaginary);
            if (absImaginary % 1 == 0) {
                if(absImaginary != 1) {
                    sb.append((int) absImaginary);
                }
            } else {
                sb.append(roundToFourDecimals(absImaginary));
            }
            sb.append("i");
        }

        return sb.toString();
    }

    private double roundToFourDecimals(double value) {
        double roundedValue = Math.round(value * 10000) / 10000.0;
        if (Math.abs(roundedValue) % 1.0 == 0.0) {
            return (int) roundedValue;
        }
        return roundedValue;
    }
}
