package tasks.integerDivision;


import java.util.ArrayList;
import java.util.List;

public class ViewDivision {
    private int number;
    private int divisor;
    private int indexInsertion;
    private List<Integer> dividers;
    private List<Integer> remains;
    private List<Integer> partNums;

    public void drawDivisionTable(int number, int divisor, List<Integer> dividers, List<Integer> remains, List<Integer> partNums) {
        setNumber(number);
        setDivisor(divisor);
        setDividers(dividers);
        setRemains(remains);
        setPartNums(partNums);
        drawHead();
        drawBody();
    }

    private void drawHead () {
        String viewHead = String.format("%d|%d\n%s|%d",
                number, divisor, drawLine(0, String.valueOf(dividers.get(0))), number/divisor);
        System.out.println(viewHead);
    }

    private void drawBody() {
        for (int i = 1; i < remains.size(); i++) {
            System.out.println(drawLine(indexInsertion, String.valueOf(partNums.get(i))));
            System.out.println(drawLine(indexInsertion, String.valueOf(dividers.get(i))));
            int dividerLenght = String.valueOf(partNums.get(i)).length();
            int remainLenght = String.valueOf(remains.get(i - 1)).length();
            if (remains.get(i) != 0)
                indexInsertion += dividerLenght - remainLenght;
            else
                indexInsertion += dividerLenght - remainLenght + 1;
        }
        indexInsertion = String.valueOf(number).length() - String.valueOf(remains.get(remains.size() - 1)).length();
        System.out.println(drawLine(indexInsertion, String.valueOf(remains.get(remains.size() - 1))));
    }

    private String drawLine (int indexInsertion, String str) {
        int numLength = String.valueOf(number).length();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < numLength - str.length(); i++)
            line.append(" ");
        return line.insert(indexInsertion, str).toString();
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public void setDividers(List<Integer> dividers) {
        this.dividers = dividers;
    }

    public void setRemains(List<Integer> remains) {
        this.remains = remains;
    }

    public void setPartNums(List<Integer> partNums) {
        this.partNums = partNums;
    }
}
