/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * This class is a BZModel class which implements the Model interface
 * All this class does is generate a 2d array filled with BZ cells
 */

 package Models;

 import java.util.HashMap;
 import Cells.BZCell;
 import Cells.Cell;

public class BZModel implements Model {

    // Generate grid with given size
    @Override
    public Cell[][] genGrid(int numRow, int numCol) {

        // Create numRow x numCol grid
        BZCell[][] BZGrid = new BZCell[numRow][numCol];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                
                // Create a new BZ cell
                BZCell newCell = new BZCell();
                // Put cell in grid
                BZGrid[i][j] = newCell;
            }            
        }

        return BZGrid;
    }

    
    // Generate grid with given size and concentration or parameters
    @Override
    public Cell[][] genGrid(int numRow, int numCol, HashMap<String, Float> input, String type){
        
        // Create a new grid
        BZCell[][] BZGrid = new BZCell[numRow][numCol];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                
                // If type is concentration
                if (type.equals("concentration")){

                    // Create a new cell
                    BZCell newCell = new BZCell();
                    // Set the concentration to the input
                    newCell.setConcentrations(input);
                    // Add cell to grid
                    BZGrid[i][j] = newCell;
                }

                // If type is parameters
                if (type.equals("parameters")){

                    // Create a new cell
                    BZCell newCell = new BZCell();
                    // Set the parameters to the input
                    newCell.setParameters(input);
                    // Add cell to grid
                    BZGrid[i][j] = newCell;
                }

            }
        }

        return BZGrid;
    }


    // Method creates a new grid with concentrations and parameters
    public Cell[][] genGrid(int numRow, int numCol, HashMap<String, Float> parameters, HashMap<String, Float> concentrations){

        BZCell[][] BZGrid = new BZCell[numRow][numCol];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                
                // Create new BZ Cell
                BZCell newCell = new BZCell();
                // Setting input concentrations
                newCell.setConcentrations(concentrations);
                // Setting input parameters
                newCell.setParameters(parameters);

                BZGrid[i][j] = newCell;
            }
        }
        return BZGrid;
    }

    
    // Method overrides to inherited toString method
    public String toString(){
        return "Belousov-Zhabotinsky Model";
    }
    
}
