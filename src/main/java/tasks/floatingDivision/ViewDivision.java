package tasks.floatingDivision;


import java.util.List;

public class ViewDivision {
    private int number;
    private int divisor;
    private int indexInsertion;
    private List<Integer> nearestDivisorNums;
    private List<Integer> numRemains;
    private List<Integer> partialNums;

    public ViewDivision(int number, int divisor, List<Integer> nearestDivisorNums,
                        List<Integer> numRemains, List<Integer> partialNums) {
        this.number = number;
        this.divisor = divisor;
        this.nearestDivisorNums = nearestDivisorNums;
        this.numRemains = numRemains;
        this.partialNums = partialNums;
    }

    public void drawDivisionTable() {
        if (number < divisor) {
            String viewHead = String.format("%d|%d\n%s|%.10f",
                    number, divisor, drawLine(0, " "), (float)number/divisor);
            System.out.println(viewHead);
            System.out.println("--------------");
        } else {
            drawHead();
            drawBody();
            System.out.println("--------------");
        }
    }

    private void drawHead () {
        String viewHead = String.format("%d|%d\n%s|%.10f",
                number, divisor, drawLine(0, String.valueOf(nearestDivisorNums.get(0))), (float)number/divisor);
        System.out.println(viewHead);
    }

    private void drawBody() {
        for (int i = 1; i < numRemains.size(); i++) {
            float partialNumLenght = (float) String.valueOf(partialNums.get(i - 1)).length();
            float numRemainLenght = (float) String.valueOf(numRemains.get(i - 1)).length();
            if (numRemains.get(i - 1) != 0)
                indexInsertion += partialNumLenght - numRemainLenght;
            else
                indexInsertion += partialNumLenght;
            System.out.println(drawLine(indexInsertion, String.valueOf(partialNums.get(i))));
            System.out.println(drawLine(indexInsertion, String.valueOf(nearestDivisorNums.get(i))));
        }
        if (partialNums.size() > numRemains.size()) {
            indexInsertion = String.valueOf(number).length() - String.valueOf(partialNums.get(partialNums.size() - 1)).length();
            System.out.println(drawLine(indexInsertion, String.valueOf(partialNums.get(partialNums.size() - 1))));
        } else {
            indexInsertion = String.valueOf(number).length() - String.valueOf(numRemains.get(numRemains.size() - 1)).length();
            System.out.println(drawLine(indexInsertion, String.valueOf(numRemains.get(numRemains.size() - 1))));
        }
    }

    private String drawLine (int indexInsertion, String str) {
        int numLength = String.valueOf(number).length();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < indexInsertion + numLength - str.length(); i++)
            line.append(" ");
        return line.insert(indexInsertion, str).toString();
    }
}
