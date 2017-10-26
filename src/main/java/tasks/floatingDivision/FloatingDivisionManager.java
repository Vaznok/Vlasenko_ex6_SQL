package tasks.floatingDivision;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FloatingDivisionManager {
    private static List<Integer> nearestDivisorNums = new ArrayList();
    private static List<Integer> numRemains = new ArrayList();
    private static List<Integer> partialNums = new ArrayList<>();
    private static String result;


    public static void makeDivision(int number, int divisor) {
        makeDivision(number, divisor, 7);
    }

    public static void makeDivision(int number, int divisor, int countPeriodNum) {
        if (number < 0)
            throw new IllegalArgumentException("Inputted number mustn't been less than '0'!");
        if (divisor <= 0)
            throw new IllegalArgumentException("Inputted divisor mustn't been less or equal to '0'!");
        nearestDivisorNums.clear();
        numRemains.clear();
        partialNums.clear();
        result = null;

        if (number == 0) {
            result = "0";
            ViewDivision view = new ViewDivision(number, divisor, result, null, null, null);
            view.drawDivisionTable();
            return;
        }
        divisionManager(number, divisor, countPeriodNum);
        ViewDivision view = new ViewDivision(number, divisor, result, nearestDivisorNums, numRemains, partialNums);
        view.drawDivisionTable();
    }

    private static void divisionManager(int number, int divisor, int countPeriodNum) {
        result = fractionToDecimal(number, divisor);
        if (result.contains("(")) {
            countPeriodNum = (result.substring(result.indexOf(".") + 1, result.length()).length() - 1);
        } else {
            result = new BigDecimal(Double.valueOf(result)).setScale(countPeriodNum, RoundingMode.HALF_DOWN).stripTrailingZeros().toString();
        }
        int numberLength = String.valueOf(number).length();
        int divisorLength = String.valueOf(divisor).length();
        int differenceInLength = numberLength - divisorLength;
        int tmpNumber = number;
        countPeriodNum = -1 * countPeriodNum;
        while (differenceInLength != countPeriodNum) {
            int partialNum = selectPartialNum(tmpNumber, divisor, differenceInLength);
            if(partialNum < divisor) {
                differenceInLength--;
                if (differenceInLength == countPeriodNum) {
                    partialNums.add(partialNum);
                    break;
                }
                if (differenceInLength < 0)
                    tmpNumber = partialNum;
                continue;
            }
            partialNums.add(partialNum);
            int partialDivResult = partialNum / divisor;
            int nearestDivisorNum = partialDivResult * divisor;
            int numRemain = partialNum % divisor;
            nearestDivisorNums.add(nearestDivisorNum);
            numRemains.add(numRemain);
            tmpNumber = changeTmpNum(tmpNumber, partialNum, numRemain);
            differenceInLength--;
            if (!numRemains.isEmpty()) {
                if ((numRemains.get(numRemains.size() - 1) == 0) && differenceInLength < 0) {
                    break;
                }
            }
        }
    }

    private static int selectPartialNum(int tmpNumber, int divisor, int differenceInLength) {
        StringBuilder selectPartialNum = new StringBuilder(String.valueOf(tmpNumber));
        if (differenceInLength > -1) {
            for (int i = 0; i < differenceInLength; i++) {
                selectPartialNum.deleteCharAt(selectPartialNum.length() - 1);
                int newTmpNum = Integer.parseInt(selectPartialNum.toString());
                if(newTmpNum < divisor) {
                    selectPartialNum.insert(selectPartialNum.length(), String.valueOf(tmpNumber).charAt(selectPartialNum.length()));
                    break;
                }
            }
            return Integer.valueOf(selectPartialNum.toString());
        } else {
            selectPartialNum.append("0");
            return Integer.valueOf(selectPartialNum.toString());
        }
    }

    private static int changeTmpNum(int tmpNumber, int partialNum, int numRemain) {
        StringBuilder changeTmpNum = new StringBuilder(String.valueOf(tmpNumber));
        while (Integer.valueOf(changeTmpNum.toString()) < partialNum) {
            changeTmpNum.append(0);
        }
        for (int i = 0, j = String.valueOf(partialNum).length(); i < j; i++) {
            changeTmpNum.deleteCharAt(0);
        }
        changeTmpNum.insert(0, numRemain);
        return Integer.valueOf(changeTmpNum.toString());
    }

    private static String fractionToDecimal(int numerator, int denominator) {
        String result = "";
        long num = numerator, den = denominator;
        long res = num / den;
        result += String.valueOf(res);
        long remainder = (num % den) * 10;
        if (remainder == 0)
            return result;
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        result += ".";
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg, result.length());
                result = part1 + "(" + part2 + ")";
                return result;
            }
            map.put(remainder, result.length());
            res = remainder / den;
            result += String.valueOf(res);
            remainder = (remainder % den) * 10;
        }

        return result;
    }

    static List<Integer> getNearestDivisorNums() {
        return nearestDivisorNums;
    }

    static List<Integer> getNumRemains() {
        return numRemains;
    }

    static List<Integer> getPartialNums() {
        return partialNums;
    }

    static String getResult() {
        return result;
    }
}
