/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * This file defines a model interface. A model interface just creates a grid of
 * cells following a specific model
 */

 package Models;
 import Cells.Cell;
 import java.util.HashMap;

public interface Model {

    // Method generates a grid with given rows and columns
    public abstract Cell[][] genGrid(int row, int col);

    // Method generates a grid with given rows,columns, and concentrations (or parameters)
    public abstract Cell[][] genGrid(int row, int col, HashMap<String, Float> input, String type);

    // Method generates a grid with given row, columns, concentrations, and parameters
    public abstract Cell[][] genGrid(int row, int col, HashMap<String, Float> parameters, HashMap<String, Float> concentrations);
    
}
