package com.sonartype.numbertowords.services.impl;

import com.sonartype.numbertowords.exceptions.InvalidNumberException;
import com.sonartype.numbertowords.services.ConverterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.sonartype.numbertowords.utils.ConstantsStrings.DEFAULT_DELIMITER;
import static com.sonartype.numbertowords.utils.ConstantsStrings.INVALID_NUMBER_MESSAGE;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

@Service
public class ConverterServiceImpl implements ConverterService {
    private static final Map<Integer, String> NUMBERS = new HashMap<>();
    private static final String[] LARGE_NUMBERS = {"", "thousand", "million", "billion"};
    private static final String HUNDRED = "hundred";
    private static final String NEGATIVE = "negative";
    private static final String AND_DELIMITER = "and";

    static {
        NUMBERS.put(0, "zero");
        NUMBERS.put(1, "one");
        NUMBERS.put(2, "two");
        NUMBERS.put(3, "three");
        NUMBERS.put(4, "four");
        NUMBERS.put(5, "five");
        NUMBERS.put(6, "six");
        NUMBERS.put(7, "seven");
        NUMBERS.put(8, "eight");
        NUMBERS.put(9, "nine");
        NUMBERS.put(10, "ten");
        NUMBERS.put(11, "eleven");
        NUMBERS.put(12, "twelve");
        NUMBERS.put(13, "thirteen");
        NUMBERS.put(14, "fourteen");
        NUMBERS.put(15, "fifteen");
        NUMBERS.put(16, "sixteen");
        NUMBERS.put(17, "seventeen");
        NUMBERS.put(18, "eighteen");
        NUMBERS.put(19, "nineteen");
        NUMBERS.put(20, "twenty");
        NUMBERS.put(30, "thirty");
        NUMBERS.put(40, "forty");
        NUMBERS.put(50, "fifty");
        NUMBERS.put(60, "sixty");
        NUMBERS.put(70, "seventy");
        NUMBERS.put(80, "eighty");
        NUMBERS.put(90, "ninety");
    }

    @Override
    public String numbersToWords(String numbers) {
        MultipleParameters parameters = checkAndReplaceNegativeSign(numbers);

        if (Integer.parseInt(parameters.numberWithoutSign) == 0) {
            return StringUtils.capitalize(NUMBERS.get(0));
        }

        final List<String> numbersGroup = splitAndSortNumbersByThreeChars(parameters.numberWithoutSign);

        final List<String> words = new LinkedList<>();
        for (int i = 0; i < numbersGroup.size(); i++) {
            final String currentGroup = numbersGroup.get(i);

            final int unit = Character.getNumericValue(currentGroup.charAt(0));

            int tens = (currentGroup.length() > 1) ? Character.getNumericValue(currentGroup.charAt(1)) : -1;

            final int hundreds = (currentGroup.length() > 2) ? Character.getNumericValue(currentGroup.charAt(2)) : -1;

            if (i > 0 && (unit != 0 || tens != 0 || hundreds != 0))
                words.add(LARGE_NUMBERS[i]);

            processUnitsAndTens(words, tens, unit);

            processHundreds(words, i, tens, hundreds);
        }

        if (parameters.isNegativeSignPresent) {
            words.add(NEGATIVE);
        }

        Collections.reverse(words);

        return StringUtils.capitalize(words.stream().collect(Collectors.joining(DEFAULT_DELIMITER)));
    }

    private void processUnitsAndTens(List<String> words, int tens, int unit) {
        if (tens == 1 && (tens * 10 + unit) % 10 != 0) {
            words.add(NUMBERS.get(tens * 10 + unit));
        } else {
            if (unit != 0) {
                words.add(NUMBERS.get(unit));
            }

            if (tens > -1 && tens != 0) {
                tens *= 10;
                words.add(NUMBERS.get(tens));
            }
        }
    }

    private void processHundreds(List<String> words, int numbersGroupIndex, int tens, int hundreds){
        if (hundreds > -1 && hundreds != 0) {
            if (numbersGroupIndex == 0 && tens > 0) {
                words.add(AND_DELIMITER);
            }

            words.add(HUNDRED);
            words.add(NUMBERS.get(hundreds));
        }
    }

    private List<String> splitAndSortNumbersByThreeChars(String numbers) {
        List<String> numbersGroup = new LinkedList<>();

        StringBuilder numbersStringReverse = new StringBuilder(numbers);
        numbersStringReverse.reverse();

        int len = numbersStringReverse.toString().length();
        for (int i = 0; i < len; i += 3) {
            numbersGroup.add(numbersStringReverse.toString().substring(i, Math.min(len, i + 3)));
        }

        return numbersGroup;
    }

    private MultipleParameters checkAndReplaceNegativeSign(String numbers) {
        if (numbers == null || !isCreatable(numbers)) {
            throw new InvalidNumberException(INVALID_NUMBER_MESSAGE);
        }

        if (numbers.trim().indexOf('-') != -1) {
            return new MultipleParameters(true, numbers.replaceAll("-", ""));
        }

        if (numbers.trim().indexOf('+') != -1) {
            return new MultipleParameters(false, numbers.replaceAll("\\+", ""));
        }

        return new MultipleParameters(false, numbers);
    }

    class MultipleParameters {
        public final boolean isNegativeSignPresent;
        public final String numberWithoutSign;

        public MultipleParameters(boolean isNegativeSignPresent, String numberWithoutSign) {
            this.isNegativeSignPresent = isNegativeSignPresent;
            this.numberWithoutSign = numberWithoutSign;
        }
    }
}
