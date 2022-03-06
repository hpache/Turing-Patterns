/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * This file defines a model interface. A model interface just creates a grid of
 * cells following a specific model
 */

public interface Model {

    // Method generates a grid with given rows and columns
    public abstract Cell[][] genGrid(int row, int col);
    
}
