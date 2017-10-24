package tasks.integerDivision;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivisionManager {
    private static List<Integer> nearestDivisorNums = new ArrayList();
    private static List<Integer> numRemains = new ArrayList();
    private static List<Integer> partialNums = new ArrayList<>();

    public static void makeDivision(int number, int divisor) {
        if (number < 0 || divisor <= 0)
            throw new IllegalArgumentException("Inputted number and divisor must been higher than '0'!");
        /*if (number < divisor)
            throw new IllegalArgumentException("Inputted number has to be higher or equal to divisor!");*/
        nearestDivisorNums.clear();
        numRemains.clear();
        partialNums.clear();
        divisionManager(number, divisor);
        ViewDivision view = new ViewDivision(number, divisor, nearestDivisorNums, numRemains, partialNums);
        view.drawDivisionTable();
    }

    private static void divisionManager(int number, int divisor) {
        int numberLength = String.valueOf(number).length();
        int divisorLength = String.valueOf(divisor).length();
        int differenceInLength = numberLength - divisorLength;

        int tmpNumber = number;
        while (differenceInLength != -1) {
            int partialNum = selectPartialNum(tmpNumber, divisor, differenceInLength);
            if(partialNum < divisor) {
                differenceInLength--;
                if (differenceInLength == -1)
                    partialNums.add(partialNum);
                continue;
            }
            partialNums.add(partialNum);
            int nearestDivisorNum = partialNum / divisor * divisor;
            int numRemain = partialNum % divisor;
            nearestDivisorNums.add(nearestDivisorNum);
            numRemains.add(numRemain);
            tmpNumber = changeTmpNum(tmpNumber, partialNum, numRemain);
            differenceInLength--;
        }
    }

    private static int selectPartialNum(int tmpNumber, int divisor, int differenceInLength) {
        StringBuilder selectPartialNum = new StringBuilder(String.valueOf(tmpNumber));
        for (int i = 0; i < differenceInLength; i++) {
            selectPartialNum.deleteCharAt(selectPartialNum.length() - 1);
            int newTmpNum = Integer.parseInt(selectPartialNum.toString());
            if(newTmpNum < divisor) {
                selectPartialNum.insert(selectPartialNum.length(), String.valueOf(tmpNumber).charAt(selectPartialNum.length()));
                break;
            }
        }
        return Integer.valueOf(selectPartialNum.toString());
    }

    private static int changeTmpNum(int tmpNumber, int partialNum, int numRemain) {
        StringBuilder changeTmpNum = new StringBuilder(String.valueOf(tmpNumber));
        for (int i = 0, j = String.valueOf(partialNum).length(); i < j; i++) {
            changeTmpNum.deleteCharAt(0);
        }
        changeTmpNum.insert(0, numRemain);
        return Integer.valueOf(changeTmpNum.toString());
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
}
