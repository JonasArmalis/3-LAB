package com.example.a3_lab;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalculatorCoreTest {

    @Test
    public void parse_validInteger_returnsCorrectDouble() {
        double result = CalculatorCore.parse("42");
        assertEquals(42.0, result, 0.0001);
    }

    @Test
    public void parse_validFraction_returnsCorrectDouble() {
        double result = CalculatorCore.parse("3.14");
        assertEquals(3.14, result, 0.0001);
    }

    @Test
    public void parse_invalidString_returnsZero() {
        double result = CalculatorCore.parse("abc");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void format_integerValue_trimsDotZero() {
        String formatted = CalculatorCore.format(5.0);
        assertEquals("5", formatted);
    }

    @Test
    public void format_fraction_keepsFractionPart() {
        String formatted = CalculatorCore.format(2.5);
        assertEquals("2.5", formatted);
    }

    @Test
    public void format_negativeNumber_formattedCorrectly() {
        String formatted = CalculatorCore.format(-7.0);
        assertEquals("-7", formatted);
    }
}
