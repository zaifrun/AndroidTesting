package org.projects.androidtesting;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CalculatorUnitTest {

    static Calculator calc;
    @BeforeClass
    public static void setup()
    {
        calc = new Calculator();
    }

    @Test
    public void addition_test() throws Exception {
        assertEquals(4, calc.addition(2,2));
    }

    @Test
    public void multiplication_test() throws Exception {
        assertEquals(24, calc.multiplication(6,4));
    }
}