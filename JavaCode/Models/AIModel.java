/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * This file holds the AIModel class which implements the Model interface
 * This class just generates a 2d array filled with AICell objects
 */

 package Models;

 import Cells.AICell;
 import Cells.Cell;
 import java.util.HashMap;

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


    // Generate grid with given size and concentration or parameters
    @Override
    public Cell[][] genGrid(int numRow, int numCol, HashMap<String, Float> input, String type){
        
        // Create a new grid
        AICell[][] AIGrid = new AICell[numRow][numCol];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                
                // If type is concentration
                if (type.equals("concentration")){

                    // Create a new cell
                    AICell newCell = new AICell();
                    // Set the concentration to the input
                    newCell.setConcentrations(input);
                    // Add cell to grid
                    AIGrid[i][j] = newCell;
                }

                // If type is parameters
                if (type.equals("parameters")){

                    // Create a new cell
                    AICell newCell = new AICell();
                    // Set the parameters to the input
                    newCell.setParameters(input);
                    // Add cell to grid
                    AIGrid[i][j] = newCell;
                }

            }
        }

        return AIGrid;
    }


    // Method creates a new grid with concentrations and parameters
    public Cell[][] genGrid(int numRow, int numCol, HashMap<String, Float> parameters, HashMap<String, Float> concentrations){

        AICell[][] AIGrid = new AICell[numRow][numCol];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                
                // Create new BZ Cell
                AICell newCell = new AICell();
                // Setting input concentrations
                newCell.setConcentrations(concentrations);
                // Setting input parameters
                newCell.setParameters(parameters);

                AIGrid[i][j] = newCell;
            }
        }
        return AIGrid;
    }
    
    // Method overrides inherited toString method
    public String toString(){
        return "Activator-Inhibitor Model";
    }
}
