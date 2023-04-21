package pl.edu.pw.ee.calculations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComplexNumberTest {
    @Test
    public void testAdd() {
        ComplexNumber add_1_1 = new ComplexNumber(2, 0), add_1_2 = new ComplexNumber(6.5, 0); 
        ComplexNumber add_2_1 = new ComplexNumber(0, 4), add_2_2 = new ComplexNumber(12, 0); 
        ComplexNumber add_3_1 = new ComplexNumber(21.5, 4), add_3_2 = new ComplexNumber(-15.16, -10.6); 

        String result_1 = "8.5", result_2 = "12 + 4i", result_3 = "6.34 - 6.6i";

        assertEquals(result_1, add_1_1.add(add_1_2).toString());
        assertEquals(result_2, add_2_1.add(add_2_2).toString());
        assertEquals(result_3, add_3_1.add(add_3_2).toString());
    }

    @Test
    public void testDivide() {

    }

    @Test
    public void testMultiply() {

    }

    @Test
    public void testPerformOperation() {

    }

    @Test
    public void testPowerOf() {

    }

    @Test
    public void testSubstract() {

    }

    @Test
    public void testToString() {
        ComplexNumber testedNumOne = new ComplexNumber(32, 15); 
        ComplexNumber testedNumTwo = new ComplexNumber(32.15, -15.1212); 
        ComplexNumber testedNumThree = new ComplexNumber(-32.15, 15.1212); 
        ComplexNumber testedNumFour = new ComplexNumber(-32.15, -15.1212); 

        String resultOne = "32 + 15i";
        String resultTwo = "32.15 - 15.1212i";
        String resultThree = "-32.15 + 15.1212i";
        String resultFour = "-32.15 - 15.1212i";

        assertEquals(resultOne, testedNumOne.toString());
        assertEquals(resultTwo, testedNumTwo.toString());
        assertEquals(resultThree, testedNumThree.toString());
        assertEquals(resultFour, testedNumFour.toString());
    }
}
