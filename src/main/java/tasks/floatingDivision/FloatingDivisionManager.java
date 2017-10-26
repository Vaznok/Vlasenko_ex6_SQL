package tasks.floatingDivision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FloatingDivisionManager {
    private static List<Integer> nearestDivisorNums = new ArrayList();
    private static List<Integer> numRemains = new ArrayList();
    private static List<Integer> partialNums = new ArrayList<>();
    private static StringBuilder tmpResult;
    private static String result;
    private static boolean periodStarted = false;
    private static boolean hasRecursPeriod = false;

    public static void makeDivision(int number, int divisor) {
        makeDivision(number, divisor, 7);
    }

    public static void makeDivision(int number, int divisor, int countPeriodNum) {
        countPeriodNum = -1 * countPeriodNum;
        if (number < 0)
            throw new IllegalArgumentException("Inputted number mustn't been less than '0'!");
        if (divisor <= 0)
            throw new IllegalArgumentException("Inputted divisor mustn't been less or equal to '0'!");
        nearestDivisorNums.clear();
        numRemains.clear();
        partialNums.clear();
        tmpResult = new StringBuilder();
        result = null;
        periodStarted = false;

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
        String numAfterComma = fractionToDecimal(number, divisor);
        if (numAfterComma.contains("(")) {
            hasRecursPeriod = true;
            countPeriodNum = (numAfterComma.length() - 1) * -1;
        }
        int numberLength = String.valueOf(number).length();
        int divisorLength = String.valueOf(divisor).length();
        int differenceInLength = numberLength - divisorLength;

        int tmpNumber = number;
        while (differenceInLength != countPeriodNum) {
            if (differenceInLength == -1) {
                if (number < divisor) {
                    tmpResult.append("0.");
                } else {
                    tmpResult.append(".");
                }
                periodStarted = true;
            }
            int partialNum = selectPartialNum(tmpNumber, divisor, differenceInLength);
            if(partialNum < divisor) {
                differenceInLength--;
                if (differenceInLength == countPeriodNum) {
                    partialNums.add(partialNum);
                    break;
                }
                if (differenceInLength < 0) {
                    tmpNumber = partialNum;
                    if (periodStarted) {
                        tmpResult.append("0");
                    }
                }
                continue;
            }
            partialNums.add(partialNum);
            int partialDivResult = partialNum / divisor;
            int nearestDivisorNum = partialDivResult * divisor;
            int numRemain = partialNum % divisor;
            nearestDivisorNums.add(nearestDivisorNum);
            numRemains.add(numRemain);
            tmpResult.append(String.valueOf(partialDivResult));
            tmpNumber = changeTmpNum(tmpNumber, partialNum, numRemain);
            differenceInLength--;
            if (!numRemains.isEmpty()) {
                if ((numRemains.get(numRemains.size() - 1) == 0) && differenceInLength < 0) {
                    break;
                }
            }
        }
        result = tmpResult.toString();
        if (hasRecursPeriod) {
            String newResult = result.substring(0, result.indexOf(".") + 1);
            result = new StringBuilder(newResult).append(numAfterComma).toString();
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
        long num = numerator, den = denominator;
        long res = num / den;
        long remainder = (num % den) * 10;

        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        String result = "";
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
