package org.example;

import java.util.AbstractMap;

public class ColumnTask extends ThreadMatrix {

    public ColumnTask(int startRow, int startColumn, int positionsToFill, Matrix matrix1, Matrix matrix2, Matrix result){
        super(startRow, startColumn, positionsToFill, matrix1, matrix2, result);
    }

    @Override
    protected void computeElements() {
        int currentRow = startRow;
        int currentColumn = startColumn;

        int countPositionsToFill = positionsToFill;

        int noRows = result.getRows();
        int noCols = result.getColumns();

        while(countPositionsToFill > 0 && currentRow < noRows && currentColumn < noCols){

            elems.add(new AbstractMap.SimpleEntry<>(currentRow, currentColumn));
            currentRow++;
            countPositionsToFill--;

            if(currentRow == noRows){
                currentRow = 0;
                currentColumn++;
            }
        }
    }

}
