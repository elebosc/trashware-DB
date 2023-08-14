/*
 * CREDITS: https://github.com/martapanc/ItalianFiscalCodeCalculator
 */

package it.unibo.populator.utils.fiscalcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static it.unibo.populator.utils.fiscalcode.FunctionChecks.howManyConsonants;
import static it.unibo.populator.utils.fiscalcode.FunctionChecks.isAllLetters;
import static it.unibo.populator.utils.fiscalcode.FunctionChecks.isDateValid;
import static it.unibo.populator.utils.fiscalcode.FunctionChecks.isYearValid;
import static it.unibo.populator.utils.fiscalcode.FunctionChecks.replaceSpecialChars;
import static it.unibo.populator.utils.fiscalcode.NameAndSurnameComputations.pickFirstConsonantAndFirstTwoVowels;
import static it.unibo.populator.utils.fiscalcode.NameAndSurnameComputations.pickFirstThirdAndFourthConsonant;
import static it.unibo.populator.utils.fiscalcode.NameAndSurnameComputations.pickFirstThreeConsonants;
import static it.unibo.populator.utils.fiscalcode.NameAndSurnameComputations.pickFirstThreeVowels;
import static it.unibo.populator.utils.fiscalcode.NameAndSurnameComputations.pickFirstTwoConsonantsAndFirstVowel;

public class ComputeFiscalCode {

    public static String computeSurname(String input) {
        input = replaceSpecialChars(input);

        if (isAllLetters(input)) {
            StringBuilder result = new StringBuilder();
            input = input.toUpperCase();

            if (input.length() < 3) {
                result = new StringBuilder(input);
                while (result.length() < 3) {
                    result.append("X");
                }
            } else {
                switch (howManyConsonants(input)) {
                    case 0:
                        result.append(pickFirstThreeVowels(input));
                        break;
                    case 1:
                        result.append(pickFirstConsonantAndFirstTwoVowels(input));
                        break;
                    case 2:
                        result.append(pickFirstTwoConsonantsAndFirstVowel(input));
                        break;
                    default:
                        result.append(pickFirstThreeConsonants(input));
                }
            }
            return result.toString();
        } else {
            throw new IllegalArgumentException("Error: surname contains invalid characters.");
        }
    }

    public static String computeName(String inputName) {
        inputName = replaceSpecialChars(inputName);
        if (isAllLetters(inputName)) {
            StringBuilder result = new StringBuilder();
            inputName = inputName.toUpperCase();

            if (inputName.length() < 3) {
                result = new StringBuilder(inputName);
                while (result.length() < 3)
                    result.append("X");
            } else {
                switch (howManyConsonants(inputName)) {
                    case 0:
                        result.append(pickFirstThreeVowels(inputName));
                        break;
                    case 1:
                        result.append(pickFirstConsonantAndFirstTwoVowels(inputName));
                        break;
                    case 2:
                        result.append(pickFirstTwoConsonantsAndFirstVowel(inputName));
                        break;
                    case 3:
                        result.append(pickFirstThreeConsonants(inputName));
                        break;
                    default:
                        result.append(pickFirstThirdAndFourthConsonant(inputName));
                }
            }
            return result.toString();

        } else {
            throw new IllegalArgumentException("Error: surname contains invalid characters.");
        }
    }

    public static String computeDateOfBirth(int day, int month, int year, String gender) {

        if (isYearValid(year)) {
            if (isDateValid(day, month, year)) {
                String result = "";
                // get the last 2 digits of the year
                if (year % 100 >= 10) {
                    result += (year % 100);
                } else {
                    result = result + 0 + (year % 100);
                }
                // get the letter corresponding to the month
                switch (month) {
                    case 1: {
                        result += "A";
                        break;
                    }
                    case 2: {
                        result += "B";
                        break;
                    }
                    case 3: {
                        result += "C";
                        break;
                    }
                    case 4: {
                        result += "D";
                        break;
                    }
                    case 5: {
                        result += "E";
                        break;
                    }
                    case 6: {
                        result += "H";
                        break;
                    }
                    case 7: {
                        result += "L";
                        break;
                    }
                    case 8: {
                        result += "M";
                        break;
                    }
                    case 9: {
                        result += "P";
                        break;
                    }
                    case 10: {
                        result += "R";
                        break;
                    }
                    case 11: {
                        result += "S";
                        break;
                    }
                    case 12: {
                        result += "T";
                        break;
                    }
                }
                switch (gender) {
                    case "f": {
                        result += (day + 40);
                        break;
                    }
                    case "m": {
                        result += (day <= 10 ? "0" + day : day);
                        break;
                    }
                }
                return result;
            } else {
                throw new IllegalArgumentException("Error: invalid birthday date");
            }
        } else {
            throw new IllegalArgumentException("Error: invalid year in birthday date");
        }
    }

    public static String computeTownOfBirth(String townString) throws IOException {
        List<Town> townList = new ArrayList<>();
        String townCode = "0";
        townString = townString.toUpperCase();
        try (BufferedReader read =
                     new BufferedReader(
                             new InputStreamReader(ComputeFiscalCode.class.getResourceAsStream("/TownCodeList.txt")))) {
            String line = read.readLine();
            String[] town;
            while (line != null) {
                town = line.split(";");
                townList.add(new Town(town[0], town[1]));
                line = read.readLine();
            }
            int i = 0;
            while (i < townList.size()) {
                if (townString.equals(townList.get(i).getTownName())) {
                    townCode = townList.get(i).getTownCode();
                    break;
                }
                i++;
            }
        }

        return townCode;
    }

    public static String computeControlChar(String incompleteFiscalCode) {
        String control = "";
        int evenSum = 0, oddSum = 0;
        incompleteFiscalCode = incompleteFiscalCode.toUpperCase();
        if (incompleteFiscalCode.length() == 15) {
            oddSum = ComputeFiscalCode.getOddSum(incompleteFiscalCode);

            evenSum = ComputeFiscalCode.getEvenSum(incompleteFiscalCode);

            // The remainder of the division is the control character
            int sum = (oddSum + evenSum) % 26;
            switch (sum) {
                case 0: {
                    control = "A";
                    break;
                }
                case 1: {
                    control = "B";
                    break;
                }
                case 2: {
                    control = "C";
                    break;
                }
                case 3: {
                    control = "D";
                    break;
                }
                case 4: {
                    control = "E";
                    break;
                }
                case 5: {
                    control = "F";
                    break;
                }
                case 6: {
                    control = "G";
                    break;
                }
                case 7: {
                    control = "H";
                    break;
                }
                case 8: {
                    control = "I";
                    break;
                }
                case 9: {
                    control = "J";
                    break;
                }
                case 10: {
                    control = "K";
                    break;
                }
                case 11: {
                    control = "L";
                    break;
                }
                case 12: {
                    control = "M";
                    break;
                }
                case 13: {
                    control = "N";
                    break;
                }
                case 14: {
                    control = "O";
                    break;
                }
                case 15: {
                    control = "P";
                    break;
                }
                case 16: {
                    control = "Q";
                    break;
                }
                case 17: {
                    control = "R";
                    break;
                }
                case 18: {
                    control = "S";
                    break;
                }
                case 19: {
                    control = "T";
                    break;
                }
                case 20: {
                    control = "U";
                    break;
                }
                case 21: {
                    control = "V";
                    break;
                }
                case 22: {
                    control = "W";
                    break;
                }
                case 23: {
                    control = "X";
                    break;
                }
                case 24: {
                    control = "Y";
                    break;
                }
                case 25: {
                    control = "Z";
                    break;
                }
            }
        }
        return control;
    }

    private static int getOddSum(final String incompleteFiscalCode) {
        // calculate sum of character values at odd position
        int oddSum = 0;
        for (int i = 1; i <= 15; i += 2) {
            switch (incompleteFiscalCode.charAt(i - 1)) {
                case '0': {
                    oddSum += 1;
                    break;
                }
                case '1': {
                    oddSum += 0;
                    break;
                }
                case '2': {
                    oddSum += 5;
                    break;
                }
                case '3': {
                    oddSum += 7;
                    break;
                }
                case '4': {
                    oddSum += 9;
                    break;
                }
                case '5': {
                    oddSum += 13;
                    break;
                }
                case '6': {
                    oddSum += 15;
                    break;
                }
                case '7': {
                    oddSum += 17;
                    break;
                }
                case '8': {
                    oddSum += 19;
                    break;
                }
                case '9': {
                    oddSum += 21;
                    break;
                }
                case 'A': {
                    oddSum += 1;
                    break;
                }
                case 'B': {
                    oddSum += 0;
                    break;
                }
                case 'C': {
                    oddSum += 5;
                    break;
                }
                case 'D': {
                    oddSum += 7;
                    break;
                }
                case 'E': {
                    oddSum += 9;
                    break;
                }
                case 'F': {
                    oddSum += 13;
                    break;
                }
                case 'G': {
                    oddSum += 15;
                    break;
                }
                case 'H': {
                    oddSum += 17;
                    break;
                }
                case 'I': {
                    oddSum += 19;
                    break;
                }
                case 'J': {
                    oddSum += 21;
                    break;
                }
                case 'K': {
                    oddSum += 2;
                    break;
                }
                case 'L': {
                    oddSum += 4;
                    break;
                }
                case 'M': {
                    oddSum += 18;
                    break;
                }
                case 'N': {
                    oddSum += 20;
                    break;
                }
                case 'O': {
                    oddSum += 11;
                    break;
                }
                case 'P': {
                    oddSum += 3;
                    break;
                }
                case 'Q': {
                    oddSum += 6;
                    break;
                }
                case 'R': {
                    oddSum += 8;
                    break;
                }
                case 'S': {
                    oddSum += 12;
                    break;
                }
                case 'T': {
                    oddSum += 14;
                    break;
                }
                case 'U': {
                    oddSum += 16;
                    break;
                }
                case 'V': {
                    oddSum += 10;
                    break;
                }
                case 'W': {
                    oddSum += 22;
                    break;
                }
                case 'X': {
                    oddSum += 25;
                    break;
                }
                case 'Y': {
                    oddSum += 24;
                    break;
                }
                case 'Z': {
                    oddSum += 23;
                    break;
                }
            }
        }

        return oddSum;
    }

    private static int getEvenSum(final String incompleteFiscalCode) {
        // calculate sum of character values at even position
        int evenSum = 0;
        for (int i = 2; i <= 14; i += 2) {
            switch (incompleteFiscalCode.charAt(i - 1)) {
                case '0': {
                    evenSum += 0;
                    break;
                }
                case '1': {
                    evenSum += 1;
                    break;
                }
                case '2': {
                    evenSum += 2;
                    break;
                }
                case '3': {
                    evenSum += 3;
                    break;
                }
                case '4': {
                    evenSum += 4;
                    break;
                }
                case '5': {
                    evenSum += 5;
                    break;
                }
                case '6': {
                    evenSum += 6;
                    break;
                }
                case '7': {
                    evenSum += 7;
                    break;
                }
                case '8': {
                    evenSum += 8;
                    break;
                }
                case '9': {
                    evenSum += 9;
                    break;
                }
                case 'A': {
                    evenSum += 0;
                    break;
                }
                case 'B': {
                    evenSum += 1;
                    break;
                }
                case 'C': {
                    evenSum += 2;
                    break;
                }
                case 'D': {
                    evenSum += 3;
                    break;
                }
                case 'E': {
                    evenSum += 4;
                    break;
                }
                case 'F': {
                    evenSum += 5;
                    break;
                }
                case 'G': {
                    evenSum += 6;
                    break;
                }
                case 'H': {
                    evenSum += 7;
                    break;
                }
                case 'I': {
                    evenSum += 8;
                    break;
                }
                case 'J': {
                    evenSum += 9;
                    break;
                }
                case 'K': {
                    evenSum += 10;
                    break;
                }
                case 'L': {
                    evenSum += 11;
                    break;
                }
                case 'M': {
                    evenSum += 12;
                    break;
                }
                case 'N': {
                    evenSum += 13;
                    break;
                }
                case 'O': {
                    evenSum += 14;
                    break;
                }
                case 'P': {
                    evenSum += 15;
                    break;
                }
                case 'Q': {
                    evenSum += 16;
                    break;
                }
                case 'R': {
                    evenSum += 17;
                    break;
                }
                case 'S': {
                    evenSum += 18;
                    break;
                }
                case 'T': {
                    evenSum += 19;
                    break;
                }
                case 'U': {
                    evenSum += 20;
                    break;
                }
                case 'V': {
                    evenSum += 21;
                    break;
                }
                case 'W': {
                    evenSum += 22;
                    break;
                }
                case 'X': {
                    evenSum += 23;
                    break;
                }
                case 'Y': {
                    evenSum += 24;
                    break;
                }
                case 'Z': {
                    evenSum += 25;
                    break;
                }
            }
        }
        return evenSum;
    }
}
