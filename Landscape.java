/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * This file holds the Landscape class, all this is responsible for
 * is putting our cells in a grid in order to visualize them
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Graphics;

public class Landscape {

    // Field for grid of cells
    private Cell[][] grid;
    // Field for number of columns
    private int numCols;
    // Field for number of rows
    private int numRows;
    // Field for a model interface
    private Model model;

    // Constructor with custom rows, columns, and model
    public Landscape(int rows, int cols, Model model){

        // Initializing numRows field
        this.numRows = rows;
        // Initializing numCols field
        this.numCols = cols;
        // Initializing model field
        this.model = model;

        // Running the generate grid method
        this.generateGrid();
    }

    // Method generates a grid from the model interface
    private void generateGrid(){
        
        // Initialize the grid field with the output grid
        this.grid = this.model.genGrid(this.numRows, this.numCols);
    }

    // Method generates a clone grid
    private Cell[][] generateClone(){

        return this.model.genGrid(this.numRows, this.numCols);
    }


    // Method resets all the cells in the grid
    public void reset(){

        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid.length; col++) {
                
                // Getting ijth cell
                Cell currentCell = this.grid[row][col];

                currentCell.reset();
            }
        }
    }


    // Method returns the grid of cells
    public Cell[][] getGrid() {return this.grid;}


    // Method returns the number of columns
    public int getColsNum() {return this.numCols;}


    // Method returns the number of rows
    public int getRowsNum() {return this.numRows;}


    // Method returns cell at specified location
    public Cell getCell(int row, int col) { return this.grid[row][col];}


    // Auxiliary method scans 2d array elements in a given row
    // Implements warping
    private ArrayList<Cell> scanRow(int row, int initialColumn, int finalColumn){

        // Initializing scanned arraylist
        ArrayList<Cell> scannedElements = new ArrayList<Cell>();

        // Iterate over the columns at [row] starting from initialColumn to finalColumn
        for (int j = initialColumn; j <= finalColumn; j++){

            // Calculating warped indices (Toroidal warping)
            int warpedI = (row + this.numRows) % this.numRows;
            int warpedJ = (j + this.numCols) % this.numCols;

            // Getting cellElement at warped indices
            Cell cellElement = this.grid[warpedI][warpedJ];

            // Assigning it to the scannedElements ArrayList
            scannedElements.add(cellElement);
        }
        return scannedElements;
    }

    // Method gets neighbors of a given cell
    public ArrayList<Cell> getNeighbors(int row, int col){
        
        // Initializing neighbors arraylist
        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        // Getting the rows and columns that contain the neighbors
        int topRow = row - 1;
        int lowerRow = row + 1;
        int initialColumn = col - 1; 
        int finalColumn = col + 1;

        // Getting neighbors in the top row
        ArrayList<Cell> topNeighbors = this.scanRow(topRow, initialColumn, finalColumn);
        // Getting neighbors in the same row
        ArrayList<Cell> sameRowNeighbors = this.scanRow(row, initialColumn, finalColumn);
        //Getting neighbors in the lower row
        ArrayList<Cell> lowNeighbors = this.scanRow(lowerRow, initialColumn, finalColumn);

        // Adding all ArrayLists the neighbors ArrayList
        neighbors.addAll(topNeighbors);
        neighbors.addAll(sameRowNeighbors);
        neighbors.addAll(lowNeighbors);

        return neighbors;
    }

    // Method calls on the draw method for all cells in the grid
    public void draw( Graphics g, int gridScale){

        // Iterating over all the grid elements
        for (int i = 0; i < this.numRows; i++){
            for (int j = 0; j < this.numCols; j++){

                // Picking out our ijth cell
                Cell currentCell = this.grid[i][j];
                
                // Drawing our ijth cell
                currentCell.draw(g, i * gridScale, j * gridScale, gridScale);
            }
        }
    }

    // Method advances the model one time step
    public void advance(){

        // Make a clone of the grid array so we can work on it
        Cell[][] gridClone = this.generateClone();

        // Iterate over the original grid in order to copy its ijth element
        // Once we do that, we get the ijth's cell neighbor list and update it appropriately
        // Once we update the temporary cell, we assign it to the clone array 
        for (int i = 0; i < this.numRows; i++){
            for (int j = 0; j < this.numCols; j++){

                // Create a new cell object
                Cell tempCell = gridClone[i][j];
                // Get ijth cell from the original grid
                Cell originalCell = this.grid[i][j];

                // Set the cells status equal to the one in the ijth position of the original grid
                tempCell.setConcentrations(originalCell.getConcentrations());

                // Giving the temporary cell the same reaction parameters as the original cell
                tempCell.setParameters(originalCell.getParameters());

                // Assigning tempCell to the ijth element in the clone array
                gridClone[i][j] = tempCell;

                // Get the neighbors of the ijth cell in the original grid
                ArrayList<Cell> neighbors = this.getNeighbors(i, j);

                // Update the ijth tempCell with the neighbors
                tempCell.updateState(neighbors); 
            }
        }

        // Once we are done updating the cells we just set this.grid equal to the clone array
        this.grid = gridClone;
    }


    // Method formats our grid object in a nice string
    public String toString(){

        // Initializing output string
        String output = "";

        // Iterating over 2d array
        for (int i = 0; i < this.numRows; i++){
            for (int j = 0; j < this.numCols; j++){
                output += this.grid[i][j] + " ";
            }
            output += "\n";
        }

        return output;
    }

    public static void main(String[] args) {
        
        // Creating 5 x 5 grid
        Landscape grid1 = new Landscape(15, 15, new BZModel());
        // Printing out concentrations of chemical A
        System.out.println("\nInitial time step");
        System.out.println(grid1);

        // Picking inidividual cell to pick on
        Cell cell1 = grid1.getCell(1, 1);
        // Checking concentration
        System.out.println("Concentration of cell at (1,1): " + cell1);
        // Checking class type
        System.out.println("Cell at (1,1) is of class: " + cell1.getClass());

        // Advance the grid one time step
        grid1.advance();
        System.out.println("\nAdvance one time step");

        // Print out the concentrations of chemical A after one step
        System.out.println(grid1);
        // Print the concentration of cell at (1,1) after one step
        System.out.println("Concentration of cell at (1,1): " + cell1);

        // Visualize updated grid
        LandscapeDisplay display1 = new LandscapeDisplay(grid1, 8);
    }
}
