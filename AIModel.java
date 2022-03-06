/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * This file holds the AIModel class which implements the Model interface
 * This class just generates a 2d array filled with AICell objects
 */

public class AIModel implements Model {

    @Override
    public Cell[][] genGrid(int numRow, int numCol) {

        AICell[][] AIGrid = new AICell[numRow][numCol];

        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                
                // Create new AICell
                AICell newCell = new AICell();

                AIGrid[row][col] = newCell;
            }
        }

        return AIGrid;
    }
    
}
