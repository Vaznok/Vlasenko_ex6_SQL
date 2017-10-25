package tasks.floatingDivision;


import java.util.List;

public class ViewDivision {
    private int number;
    private int divisor;
    private String result;
    private int indexInsertion;
    private List<Integer> nearestDivisorNums;
    private List<Integer> numRemains;
    private List<Integer> partialNums;

    public ViewDivision(int number, int divisor, String result, List<Integer> nearestDivisorNums,
                        List<Integer> numRemains, List<Integer> partialNums) {
        this.number = number;
        this.divisor = divisor;
        this.nearestDivisorNums = nearestDivisorNums;
        this.numRemains = numRemains;
        this.partialNums = partialNums;
        this.result = result;
    }

    public void drawDivisionTable() {
        if (number == 0) {
            String viewHead = String.format("%d|%d\n%s|%d",
                    number, divisor, drawLineForHead(0, " ", String.valueOf(number).length()), 0);
            System.out.println(viewHead);
            System.out.println("--------------");
        } else {
            drawHead();
            drawBody();
            System.out.println("--------------");
        }
    }

    private void drawHead () {
        int numLenght = String.valueOf(number).length();
        int partialNumLenght = String.valueOf(partialNums.get(0)).length();
        int nearestNumLenght = String.valueOf(nearestDivisorNums.get(0)).length();
        int differenceInLenght = partialNumLenght - nearestNumLenght;
        String viewHead;
        if (number > nearestDivisorNums.get(0)) {
            viewHead = String.format("%d|%d\n%s|%s",
                    number, divisor, drawLineForHead(differenceInLenght, String.valueOf(nearestDivisorNums.get(0)), numLenght), result);
        } else {
            viewHead = String.format("%s|%d\n%s|%s",
                    drawLineForHead(0, String.valueOf(number), nearestNumLenght), divisor,
                    drawLineForHead(differenceInLenght, String.valueOf(nearestDivisorNums.get(0)), nearestNumLenght), result);
        }
        System.out.println(viewHead);
    }

    private void drawBody() {
        for (int i = 1; i < partialNums.size(); i++) {
            int previousPartialNumLenght = String.valueOf(partialNums.get(i - 1)).length();
            int previousNumRemainLenght = String.valueOf(numRemains.get(i - 1)).length();

            int partialNumLenght = String.valueOf(partialNums.get(i)).length();
            int nearestNumLenght = String.valueOf(nearestDivisorNums.get(i)).length();
            int differenceInLenght = partialNumLenght - nearestNumLenght;

            if (numRemains.get(i - 1) != 0)
                indexInsertion += previousPartialNumLenght - previousNumRemainLenght;
            else
                indexInsertion += previousPartialNumLenght;

            System.out.println(drawLine(indexInsertion, String.valueOf(partialNums.get(i))));
            System.out.println(drawLine(indexInsertion + differenceInLenght, String.valueOf(nearestDivisorNums.get(i))));
        }
        indexInsertion += String.valueOf(partialNums.get(partialNums.size() - 1)).length() - String.valueOf(numRemains.get(numRemains.size() - 1)).length();
        if (partialNums.size() > numRemains.size()) {
            System.out.println(drawLine(indexInsertion, String.valueOf(partialNums.get(partialNums.size() - 1))));
        } else {
            System.out.println(drawLine(indexInsertion, String.valueOf(numRemains.get(numRemains.size() - 1))));
        }
    }

    private String drawLine (int indexInsertion, String str) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < indexInsertion + str.length() + 1; i++)
            line.append(" ");
        return line.insert(indexInsertion, str).toString();
    }

    private String drawLineForHead (int indexInsertion, String str, int numLength) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < numLength - str.length(); i++)
            line.append(" ");
        return line.insert(indexInsertion, str).toString();
    }
}
