package pl.edu.pw.ee.calculations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComplexNumberTest {
    @Test
    public void testAdd() {
        ComplexNumber add_1_1 = new ComplexNumber(2, 0), add_1_2 = new ComplexNumber(6.5, 0); 
        ComplexNumber add_2_1 = new ComplexNumber(0, 4), add_2_2 = new ComplexNumber(12, 0); 
        ComplexNumber add_3_1 = new ComplexNumber(21.5, 4), add_3_2 = new ComplexNumber(-15.16, -10.6); 

        String result_1 = "8,5", result_2 = "12+4i", result_3 = "6,34-6,6i";

        assertEquals(result_1, add_1_1.add(add_1_2).toString());
        assertEquals(result_2, add_2_1.add(add_2_2).toString());
        assertEquals(result_3, add_3_1.add(add_3_2).toString());
    }

    @Test
    public void testDivide() {
        ComplexNumber divide_1_1 = new ComplexNumber(10, 0), divide_1_2 = new ComplexNumber(2, 0);
        ComplexNumber divide_2_1 = new ComplexNumber(0, 8), divide_2_2 = new ComplexNumber(4, 0);
        ComplexNumber divide_3_1 = new ComplexNumber(6, 4), divide_3_2 = new ComplexNumber(-2, -2);

        String result_1 = "5", result_2 = "2i", result_3 = "-2,5+0,5i";

        assertEquals(result_1, divide_1_1.divide(divide_1_2).toString());
        assertEquals(result_2, divide_2_1.divide(divide_2_2).toString());
        assertEquals(result_3, divide_3_1.divide(divide_3_2).toString());
    }

    @Test
    public void testMultiply() {
        ComplexNumber multiply_1_1 = new ComplexNumber(2, 0), multiply_1_2 = new ComplexNumber(3, 0);
        ComplexNumber multiply_2_1 = new ComplexNumber(0, 4), multiply_2_2 = new ComplexNumber(2, 0);
        ComplexNumber multiply_3_1 = new ComplexNumber(2.5, 4), multiply_3_2 = new ComplexNumber(-1.2, -2);

        String result_1 = "6", result_2 = "8i", result_3 = "5-9,8i";

        assertEquals(result_1, multiply_1_1.multiply(multiply_1_2).toString());
        assertEquals(result_2, multiply_2_1.multiply(multiply_2_2).toString());
        assertEquals(result_3, multiply_3_1.multiply(multiply_3_2).toString());
    }

    @Test
    public void testPowerOf() {
        ComplexNumber powerOf_1_1 = new ComplexNumber(2, 0), powerOf_1_2 = new ComplexNumber(3, 0);
        ComplexNumber powerOf_2_1 = new ComplexNumber(0, 2), powerOf_2_2 = new ComplexNumber(3, 0);
        ComplexNumber powerOf_3_1 = new ComplexNumber(2, 2), powerOf_3_2 = new ComplexNumber(2, 0);
        ComplexNumber powerOf_4_1 = new ComplexNumber(16, 0), powerOf_4_2 = new ComplexNumber(0.5, 0);

        String result_1 = "8", result_2 = "-8i", result_3 = "8i", result_4 = "4";

        assertEquals(result_1, powerOf_1_1.powerOf(powerOf_1_2).toString());
        assertEquals(result_2, powerOf_2_1.powerOf(powerOf_2_2).toString());
        assertEquals(result_3, powerOf_3_1.powerOf(powerOf_3_2).toString());
        assertEquals(result_4, powerOf_4_1.powerOf(powerOf_4_2).toString());
    }

    @Test
    public void testSubstract() {
        ComplexNumber subtract_1_1 = new ComplexNumber(10, 0), subtract_1_2 = new ComplexNumber(6, 0);
        ComplexNumber subtract_2_1 = new ComplexNumber(0, 8), subtract_2_2 = new ComplexNumber(2, 0);
        ComplexNumber subtract_3_1 = new ComplexNumber(15, 4), subtract_3_2 = new ComplexNumber(5.6, -2.3);

        String result_1 = "4", result_2 = "-2+8i", result_3 = "9,4+6,3i";

        assertEquals(result_1, subtract_1_1.substract(subtract_1_2).toString());
        assertEquals(result_2, subtract_2_1.substract(subtract_2_2).toString());
        assertEquals(result_3, subtract_3_1.substract(subtract_3_2).toString());
    }

    @Test
    public void testToString() {
        ComplexNumber testedNumOne = new ComplexNumber(32, 15); 
        ComplexNumber testedNumTwo = new ComplexNumber(32.15, -15.1212); 
        ComplexNumber testedNumThree = new ComplexNumber(-32.15, 15.1212); 
        ComplexNumber testedNumFour = new ComplexNumber(-32.15, -15.1212); 

        String resultOne = "32+15i";
        String resultTwo = "32,15-15,1212i";
        String resultThree = "-32,15+15,1212i";
        String resultFour = "-32,15-15,1212i";

        assertEquals(resultOne, testedNumOne.toString());
        assertEquals(resultTwo, testedNumTwo.toString());
        assertEquals(resultThree, testedNumThree.toString());
        assertEquals(resultFour, testedNumFour.toString());
    }

    @Test (expected = ArithmeticException.class)
    public void divisionByZero() {
        new ComplexNumber(1,1).divide(new ComplexNumber(0, 0));
    }

    @Test (expected = ArithmeticException.class)
    public void powerOfImaginary() {
        new ComplexNumber(1,1).powerOf(new ComplexNumber(0, 1));
    }

    @Test (expected = ArithmeticException.class)
    public void rootedImaginary() {
        new ComplexNumber(1,1).powerOf(new ComplexNumber(2.5, 0));
    }
}
