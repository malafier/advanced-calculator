package pl.edu.pw.ee.calculations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void simpleArithmeticsTest() {
        String equasion_1 = "2+3", equasion_2 = "3+3+4-5", equasion_3 = "8+2-15", equasion_4 = "12*2", equasion_5 = "12/2", equasion_6 = "2^5"; 
        String result_1 = "5", result_2 = "5", result_3 = "-5", result_4 = "24", result_5 = "6", result_6 = "32";

        assertEquals(result_1, Calculator.getAnswer(equasion_1));
        assertEquals(result_2, Calculator.getAnswer(equasion_2));
        assertEquals(result_3, Calculator.getAnswer(equasion_3));
        assertEquals(result_4, Calculator.getAnswer(equasion_4));
        assertEquals(result_5, Calculator.getAnswer(equasion_5));
        assertEquals(result_6, Calculator.getAnswer(equasion_6));
    }

    @Test
    public void negativeNumbersTest() {
        String equasion_1 = "-4+9", equasion_2 = "3+3+(-5)", equasion_3 = "2*(-15)", equasion_4 = "-12/2";
        String result_1 = "5", result_2 = "1", result_3 = "-30", result_4 = "-6";

        assertEquals(result_1, Calculator.getAnswer(equasion_1));
        assertEquals(result_2, Calculator.getAnswer(equasion_2));
        assertEquals(result_3, Calculator.getAnswer(equasion_3));
        assertEquals(result_4, Calculator.getAnswer(equasion_4));
    }

    @Test
    public void complexArithmetics() {
        String equasion_1 = "2+2*2", equasion_2 = "(2+2i)*(3-5i)", equasion_3 = "4i+(2+2i)^(-2)";
        String result_1 = "6", result_2 = "16-4i", result_3 = "3,875i";

        assertEquals(result_1, Calculator.getAnswer(equasion_1));
        assertEquals(result_2, Calculator.getAnswer(equasion_2));
        assertEquals(result_3, Calculator.getAnswer(equasion_3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooManyRightPars() {
        Calculator.getAnswer("2-3)");
    }

    @Test (expected = NumberFormatException.class)
    public void tooManyRightParhs() {
        Calculator.getAnswer("0.00000000000001");
    }
}
