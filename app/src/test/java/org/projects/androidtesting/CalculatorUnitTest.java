package org.projects.androidtesting;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CalculatorUnitTest {

    private static Calculator calc;
    @BeforeClass
    public static void setup()
    {
        calc = new Calculator();
    }

    @Before
    public void init() {}

    @Test
    public void addition_test()  {
        assertEquals(4, calc.addition(2,2));
    }

    @Test
    public void multiplication_test() {
        assertEquals(24, calc.multiplication(6,4));
    }
}