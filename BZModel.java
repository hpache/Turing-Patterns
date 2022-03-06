/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * This class is a BZModel class which implements the Model interface
 * All this class does is generate a 2d array filled with BZ cells
 */

public class BZModel implements Model {

    @Override
    public Cell[][] genGrid(int numRow, int numCol) {

        BZCell[][] BZGrid = new BZCell[numRow][numCol];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                
                // Create a new BZ cell
                BZCell newCell = new BZCell();

                BZGrid[i][j] = newCell;
            }            
        }

        return BZGrid;
    }
    
}
