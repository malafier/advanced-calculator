package pl.edu.pw.ee.calculations;

public class ImgCalculations implements Calculations {

    @Override
    public String add(String a, String b) {
        ComplexNum aC = new ComplexNum(a), bC = new ComplexNum(b); 

        return aC.add(bC).toString(); 
    }

    @Override
    public String substract(String a, String b) {
        ComplexNum aC = new ComplexNum(a), bC = new ComplexNum(b);

        return aC.substract(bC).toString(); 
    }

    @Override
    public String multiply(String a, String b) {
        ComplexNum aC = new ComplexNum(a), bC = new ComplexNum(b);

        return aC.multiply(bC).toString();  
    }

    @Override
    public String divide(String a, String b) {
        ComplexNum aC = new ComplexNum(a), bC = new ComplexNum(b);

        return aC.divide(bC).toString(); 
    }
    
}
