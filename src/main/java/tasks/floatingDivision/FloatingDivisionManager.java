package tasks.floatingDivision;

import java.util.ArrayList;
import java.util.List;

public class FloatingDivisionManager {
    private static List<Integer> nearestDivisorNums = new ArrayList();
    private static List<Integer> numRemains = new ArrayList();
    private static List<Integer> partialNums = new ArrayList<>();
    private static StringBuilder tmpResult;
    private static String result;

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
            }
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
            if (checkingPeriodFinding(differenceInLength)) {
                tmpResult.insert(tmpResult.length(), "(" + partialDivResult + ")");
                break;
            }
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

    private static boolean checkingPeriodFinding (int differenceInLength) {
        if (numRemains.size() > 1 && differenceInLength < -1) {
            if (partialNums.get(partialNums.size() - 2) == partialNums.get(partialNums.size() - 1)) {
                partialNums.remove(partialNums.size() - 1);
                nearestDivisorNums.remove(nearestDivisorNums.size() - 1);
                numRemains.remove(numRemains.size() - 1);
                tmpResult.deleteCharAt(tmpResult.length() - 1);
                return true;
            }
        }
        return  false;
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
