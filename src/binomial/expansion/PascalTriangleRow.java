package binomial.expansion;

import java.util.PrimitiveIterator;

public class PascalTriangleRow implements PrimitiveIterator.OfInt {
    private int nextElement, generatedElementsCount;
    private final int rowNumber;

    PascalTriangleRow(int rowNumber) {
        if (rowNumber < 0) {
            throw new IllegalArgumentException("Only non-negative numbers are allowed as row numbers. " +
                    "Row counting starts from zero.");
        }
        this.rowNumber = rowNumber +2;
        nextElement = 1;
    }
    @Override
    public int nextInt() {
        int previous = nextElement;
        generatedElementsCount++;
        nextElement = previous * (rowNumber-generatedElementsCount-1) / (generatedElementsCount);
        return previous;
    }

    @Override
    public boolean hasNext() {
        return generatedElementsCount < rowNumber -1;
    }
}
