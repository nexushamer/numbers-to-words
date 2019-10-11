package com.sonartype.numbertowords.services.impl;

import com.sonartype.numbertowords.exceptions.InvalidNumberException;
import com.sonartype.numbertowords.services.ConverterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterServiceImplTest {
    private ConverterService converterService;

    @Before
    public void setUp(){
        converterService = new ConverterServiceImpl();
    }

    @Test
    public void validateResultOfWordsWithWeirdCases(){
        Assert.assertEquals("One thousand", converterService.numbersToWords("000001000"));
        Assert.assertEquals("One", converterService.numbersToWords("000000001"));
        Assert.assertEquals("Five hundred and sixty one", converterService.numbersToWords("000000561"));
        Assert.assertEquals("Eleven", converterService.numbersToWords("000000011"));
        Assert.assertEquals("Zero", converterService.numbersToWords("000000000"));
    }

    @Test
    public void validateResultOfWordsFrom1To19Numbers(){
        Assert.assertEquals("Zero", converterService.numbersToWords("0"));
        Assert.assertEquals("One", converterService.numbersToWords("1"));
        Assert.assertEquals("Two", converterService.numbersToWords("2"));
        Assert.assertEquals("Three", converterService.numbersToWords("3"));
        Assert.assertEquals("Four", converterService.numbersToWords("4"));
        Assert.assertEquals("Five", converterService.numbersToWords("5"));
        Assert.assertEquals("Six", converterService.numbersToWords("6"));
        Assert.assertEquals("Seven", converterService.numbersToWords("7"));
        Assert.assertEquals("Eight", converterService.numbersToWords("8"));
        Assert.assertEquals("Nine", converterService.numbersToWords("9"));
        Assert.assertEquals("Ten", converterService.numbersToWords("10"));
        Assert.assertEquals("Eleven", converterService.numbersToWords("11"));
        Assert.assertEquals("Twelve", converterService.numbersToWords("12"));
        Assert.assertEquals("Thirteen", converterService.numbersToWords("13"));
        Assert.assertEquals("Fourteen", converterService.numbersToWords("14"));
        Assert.assertEquals("Fifteen", converterService.numbersToWords("15"));
        Assert.assertEquals("Sixteen", converterService.numbersToWords("16"));
        Assert.assertEquals("Seventeen", converterService.numbersToWords("17"));
        Assert.assertEquals("Eighteen", converterService.numbersToWords("18"));
        Assert.assertEquals("Nineteen", converterService.numbersToWords("19"));
    }

    @Test
    public void validateResultOfWordsFrom20To99Numbers(){
        Assert.assertEquals("Twenty", converterService.numbersToWords("20"));
        Assert.assertEquals("Twenty one", converterService.numbersToWords("21"));
        Assert.assertEquals("Twenty five", converterService.numbersToWords("25"));
        Assert.assertEquals("Twenty nine", converterService.numbersToWords("29"));
        Assert.assertEquals("Thirty", converterService.numbersToWords("30"));
        Assert.assertEquals("Thirty two", converterService.numbersToWords("32"));
        Assert.assertEquals("Thirty four", converterService.numbersToWords("34"));
        Assert.assertEquals("Thirty eight", converterService.numbersToWords("38"));
        Assert.assertEquals("Forty", converterService.numbersToWords("40"));
        Assert.assertEquals("Forty three", converterService.numbersToWords("43"));
        Assert.assertEquals("Forty six", converterService.numbersToWords("46"));
        Assert.assertEquals("Forty seven", converterService.numbersToWords("47"));
        Assert.assertEquals("Fifty", converterService.numbersToWords("50"));
        Assert.assertEquals("Fifty five", converterService.numbersToWords("55"));
        Assert.assertEquals("Fifty six", converterService.numbersToWords("56"));
        Assert.assertEquals("Fifty nine", converterService.numbersToWords("59"));
        Assert.assertEquals("Sixty", converterService.numbersToWords("60"));
        Assert.assertEquals("Sixty one", converterService.numbersToWords("61"));
        Assert.assertEquals("Sixty four", converterService.numbersToWords("64"));
        Assert.assertEquals("Sixty nine", converterService.numbersToWords("69"));
        Assert.assertEquals("Seventy", converterService.numbersToWords("70"));
        Assert.assertEquals("Seventy two", converterService.numbersToWords("72"));
        Assert.assertEquals("Seventy three", converterService.numbersToWords("73"));
        Assert.assertEquals("Seventy seven", converterService.numbersToWords("77"));
        Assert.assertEquals("Eighty", converterService.numbersToWords("80"));
        Assert.assertEquals("Eighty one", converterService.numbersToWords("81"));
        Assert.assertEquals("Eighty five", converterService.numbersToWords("85"));
        Assert.assertEquals("Eighty eight", converterService.numbersToWords("88"));
        Assert.assertEquals("Ninety two", converterService.numbersToWords("92"));
        Assert.assertEquals("Ninety three", converterService.numbersToWords("93"));
        Assert.assertEquals("Ninety five", converterService.numbersToWords("95"));
        Assert.assertEquals("Ninety eight", converterService.numbersToWords("98"));
        Assert.assertEquals("Ninety nine", converterService.numbersToWords("99"));
    }

    @Test
    public void validateResultOfSomeWordsCasesFrom100To900Numbers(){
        Assert.assertEquals("One hundred", converterService.numbersToWords("100"));
        Assert.assertEquals("One hundred and thirty six", converterService.numbersToWords("136"));
        Assert.assertEquals("Three hundred", converterService.numbersToWords("300"));
        Assert.assertEquals("Three hundred and seventy eight", converterService.numbersToWords("378"));
        Assert.assertEquals("Seven hundred", converterService.numbersToWords("700"));
        Assert.assertEquals("Seven hundred and eighty one", converterService.numbersToWords("781"));
        Assert.assertEquals("Nine hundred", converterService.numbersToWords("900"));
        Assert.assertEquals("Nine hundred and forty five", converterService.numbersToWords("945"));
    }

    @Test
    public void validateResultOfWordsFrom1000AndOn(){
        Assert.assertEquals("One thousand", converterService.numbersToWords("1000"));
        Assert.assertEquals("One thousand eight hundred and thirty nine", converterService.numbersToWords("1839"));
        Assert.assertEquals("Two thousand three hundred and seventy seven", converterService.numbersToWords("2377"));
        Assert.assertEquals("Three thousand six hundred and sixty six", converterService.numbersToWords("3666"));
        Assert.assertEquals("Four thousand seven hundred and forty", converterService.numbersToWords("4740"));
        Assert.assertEquals("Nine hundred seventy four million four hundred fifty six thousand seven hundred and forty three", converterService.numbersToWords("974456743"));
        Assert.assertEquals("One million one hundred eleven thousand one hundred and eleven", converterService.numbersToWords("1111111"));
    }

    @Test
    public void validateResultOfWordsNumbersWithNegativeValues(){
        Assert.assertEquals("Negative one hundred", converterService.numbersToWords("-100"));
        Assert.assertEquals("Negative nineteen", converterService.numbersToWords("-19"));
        Assert.assertEquals("Negative two thousand five hundred and seventy", converterService.numbersToWords("-2570"));
        Assert.assertEquals("Negative thirty seven", converterService.numbersToWords("-37"));
        Assert.assertEquals("Negative seven hundred and sixty four", converterService.numbersToWords("-764"));
        Assert.assertEquals("Negative one hundred twenty four thousand seven hundred and eighty six", converterService.numbersToWords("-124786"));
        Assert.assertEquals("Negative seven million four hundred fifty two thousand three hundred and forty", converterService.numbersToWords("-7452340"));
    }

    @Test(expected = InvalidNumberException.class)
    public void validateResultWhenNumberStringIsNull(){
        converterService.numbersToWords(null);
    }

    @Test(expected = InvalidNumberException.class)
    public void validateResultWhenNumberStringAreLetters() {
        converterService.numbersToWords("ASDASDASDasdsad");
    }

    @Test(expected = InvalidNumberException.class)
    public void validateResultWhenNumberStringAreNumbersAndSymbols() {
        converterService.numbersToWords("12312213$");
    }

}