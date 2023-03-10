package pl.edu.pw.ee.calculations;

public class BasicCalculations implements Calculations {

    @Override
    public String add(String a, String b) {
        return ((Double.parseDouble(a) + Double.parseDouble(b)) + " ").trim();
    }

    @Override
    public String substract(String a, String b) {
        return ((Double.parseDouble(a) - Double.parseDouble(b)) + " ").trim();
    }

    @Override
    public String multiply(String a, String b) {
        return ((Double.parseDouble(a) * Double.parseDouble(b)) + " ").trim(); 
    }

    @Override
    public String divide(String a, String b) {
        if(b.isEmpty() || b.trim() == "0") {// to jeszcze sprawdzić
            throw new IllegalArgumentException("You can't divide by ZERO!"); 
        }

        return ((Double.parseDouble(a) / Double.parseDouble(b)) + " ").trim(); 
    }
    
}
