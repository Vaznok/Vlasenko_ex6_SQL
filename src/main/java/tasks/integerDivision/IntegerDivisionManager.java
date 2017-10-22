package tasks.integerDivision;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivisionManager {
    private static ViewDivision view = new ViewDivision();
    private static List<Integer> dividers = new ArrayList();
    private static List<Integer> remains = new ArrayList();
    private static List<Integer> partNums = new ArrayList<>();

    public static void manageDivision(int number, int divisor) {
        if (number <= 0 || divisor <= 0)
            throw new IllegalArgumentException("Inputted number and divisor must been higher than '0'!");
        if (number < divisor)
            throw new IllegalArgumentException("Inputted number has to be higher or equal to divisor!");
        divisionManager(number, divisor);
        view.drawDivisionTable(number, divisor, dividers, remains, partNums);
    }

    private static void divisionManager(int number, int divisor) {
        int numberLength = String.valueOf(number).length();
        int divisorLength = String.valueOf(divisor).length();
        int differenceInLength = numberLength - divisorLength;

        int tmpNumber = number;
        while (differenceInLength != -1) {
            int partNum = foread(tmpNumber, differenceInLength, divisor);
            partNums.add(partNum);
            if(partNum < divisor) {
                differenceInLength--;
                continue;
            }
            int divider = partNum / divisor * divisor;
            int remain = partNum % divisor;
            dividers.add(divider);
            remains.add(remain);
            tmpNumber = tmpNumber(tmpNumber, partNum, remain);
            differenceInLength--;
        }
    }

    private static int foread(int tmpNumber, int diff, int divisor) {
        StringBuilder selectNum = new StringBuilder(String.valueOf(tmpNumber));
        for (int i = 0; i < diff; i++) {
            selectNum.deleteCharAt(selectNum.length() - 1);
            int newTmpNum = Integer.parseInt(selectNum.toString());
            if(newTmpNum < divisor) {
                selectNum.insert(selectNum.length(), String.valueOf(tmpNumber).charAt(selectNum.length()));
                break;
            }
        }
        return Integer.valueOf(selectNum.toString());
    }

    private static int tmpNumber (int tmpNumber, int partNum, int remain) {
        StringBuilder changeNum = new StringBuilder(String.valueOf(tmpNumber));
        for (int i = 0, j = String.valueOf(partNum).length(); i < j; i++) {
            changeNum.deleteCharAt(0);
        }
        changeNum.insert(0, remain);
        return Integer.valueOf(changeNum.toString());
    }

   /* private static int differenceInLenght(int diff, int devider, int remain) {
        int dividerLenght = String.valueOf(devider).length();
        int remainLenght = String.valueOf(remain).length();
        int difference = dividerLenght - remainLenght;
        return diff - 1;
    }*/

}
