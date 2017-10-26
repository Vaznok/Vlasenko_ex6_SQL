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
            String viewHead = String.format("%d|%d\n%s|%s",
                    number, divisor, drawLineForHead(0, " ", String.valueOf(number).length()), result);
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
        if (Math.abs(number) > Math.abs(nearestDivisorNums.get(0))) {
            viewHead = String.format("%d|%d\n%s|%s",
                    number, divisor, drawLineForHead(differenceInLenght, String.valueOf(nearestDivisorNums.get(0)), numLenght), result);
        } else {
            viewHead = String.format("%s|%d\n%s|%s",
                    drawLineForHead(0, String.valueOf(number), partialNumLenght), divisor,
                    drawLineForHead(differenceInLenght, String.valueOf(nearestDivisorNums.get(0)), partialNumLenght), result);
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

            System.out.println(drawLineForBody(indexInsertion, String.valueOf(partialNums.get(i))));
            System.out.println(drawLineForBody(indexInsertion + differenceInLenght, String.valueOf(nearestDivisorNums.get(i))));
        }
        indexInsertion += String.valueOf(partialNums.get(partialNums.size() - 1)).length() - String.valueOf(numRemains.get(numRemains.size() - 1)).length();
        if (partialNums.size() > numRemains.size()) {
            System.out.println(drawLineForBody(indexInsertion, String.valueOf(partialNums.get(partialNums.size() - 1))));
        } else {
            System.out.println(drawLineForBody(indexInsertion, String.valueOf(numRemains.get(numRemains.size() - 1))));
        }
    }

    String drawLineForBody(int indexInsertion, String str) {
        if (indexInsertion < 0)
            throw new IllegalArgumentException();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < indexInsertion + str.length() - 1; i++)
            line.append(" ");
        return line.replace(indexInsertion, indexInsertion + str.length() - 1, str).toString();
    }

    String drawLineForHead (int indexInsertion, String str, int numLength) {
        if (indexInsertion < 0)
            throw new IllegalArgumentException();
        if (numLength < (indexInsertion + str.length()))
            throw new IllegalArgumentException();

        StringBuilder line = new StringBuilder();
        for (int i = 0; i < numLength - str.length(); i++)
            line.append(" ");
        return line.insert(indexInsertion, str).toString();
    }
}
