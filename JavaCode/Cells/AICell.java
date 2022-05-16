/**
 * Henry Pacheco Cachon
 * Created 5 March 2022
 * Activator-Inhibitor cell, this cell follows the 
 * activator-inhibitor model
 */

 package Cells;

 import java.util.ArrayList;
 import java.util.HashMap;
 import java.awt.Color;
 import java.awt.Graphics;

public class AICell extends Cell{
    
    // Defualt constructor sets the concentrations of A and B to random floats
    // Sets the parameters to 1
    public AICell(){
        super();

        // Setting concentration hashmap
        HashMap<String, Float> aiConcentrations = new HashMap<>();
        // Setting parameter hashmap
        HashMap<String, Float> aiParameters = new HashMap<>();

        // Setting concentrations for ai model 
        aiConcentrations.put("a", this.randGen.nextFloat());
        aiConcentrations.put("b", this.randGen.nextFloat());

        // Setting model parameters
        aiParameters.put("gamma", 1.0f);

        // Setting parameters and concentrations fields
        this.setConcentrations(aiConcentrations);
        this.setParameters(aiParameters);

    }

    // Constructor with custom concentrations
    public AICell(HashMap<String, Float> concentrations){
        super();

        // Getting concentrations from input
        Float a = concentrations.get("a");
        Float b = concentrations.get("b");
        // Limiting concentrations to within 0 and 0.8 
        a = this.limit(a);
        b = this.limit(b);

        // Creating model concentrations hashmap
        HashMap<String, Float> aiConcentrations = new HashMap<>();
        // Setting parameter hashmap
        HashMap<String, Float> aiParameters = new HashMap<>();

        // Setting concentrations for ai model 
        aiConcentrations.put("a", a);
        aiConcentrations.put("b", b);

        // Setting model parameters
        aiParameters.put("gamma",1.0f);

        // Updating parameters and concentrations field
        this.setParameters(aiParameters);
        this.setConcentrations(aiConcentrations);
    }

    // Method resets the models concentrations only
    public void reset(){

        // Create new concentrations hashmap
        HashMap<String, Float> resetConcentrations = new HashMap<>();

        resetConcentrations.put("a", this.randGen.nextFloat());
        resetConcentrations.put("b", this.randGen.nextFloat());

        this.setConcentrations(resetConcentrations);
    }

    // Method calculates the cells new concentrations
    public float[] activatorInhibitorModel(ArrayList<Cell> neighbors){
        // Finds average concentrations by calling on the average method
        float[] concentrations = this.average(neighbors);

        // Getting concentrations from the output of the average method
        float A = concentrations[0];
        float B = concentrations[1];

        HashMap<String, Float> parameters = this.getParameters();

        float gamma = parameters.get("gamma");
        

        // Calculating new concentrations 
        float newA = (1/16) * (16 - A * B);
        float newB = (1/16) * (A * B - B - gamma);

        // Allocating array with new concentrations
        float[] newConcentrations = {newA, newB};

        return newConcentrations;
    }


    // Method updates the state of the current cell
    public void updateState(ArrayList<Cell> neighbors){

        float[] newConcentrations = this.activatorInhibitorModel(neighbors);

        // Creating hashmap for new concentrations
        HashMap<String, Float> updateConcentrations = new HashMap<>();

        updateConcentrations.put("a", newConcentrations[0]);
        updateConcentrations.put("b", newConcentrations[1]);

        this.setConcentrations(updateConcentrations);
    }


    // Draw method for AICell
    public void draw(Graphics g, int x, int y, int scale){

        // Draw an oval representing a cell at position x,y, scaled by scale
        g.drawRect(x, y, scale, scale);

        // Setting the color in accordance to concentration A
        // Using HSB model of color
        Color cellColor = Color.getHSBColor(0.7f, 0.9f, this.getConcentrations().get("a"));
        g.setColor(cellColor);

        // Fill oval object at x,y with current collor 
        g.fillRect(x, y, scale, scale);
    }

    public static void main(String[] args) {

        // Creating input concentrations hashmap
        HashMap<String, Float> concentrations = new HashMap<>();
        
        // Putting in values for input concentrations
        concentrations.put("a", 0.1f);
        concentrations.put("b", 0.2f);

        // Creating a test AICell object
        AICell test = new AICell(concentrations);

        // Outputting the concentrations
        System.out.println(test.getConcentrations());

        // Resetting the cell
        test.reset();

        // Outputting the reset concentrations 
        System.out.println(test.getConcentrations());

        // Creating three BZ cells
        AICell cellA = new AICell();
        AICell cellB = new AICell();
        AICell cellC = new AICell();

        // Creating an array containing all the cells
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(cellA);
        cells.add(cellB);
        cells.add(cellC);

        // Printing each cell's concentration
        for (Cell cell : cells) {
            System.out.println(cell.getConcentrations());
        }

        // Getting the average of each cell chemical
        float[] averageTest = test.average(cells);

        // Printing the average for each chemical
        for (int i = 0; i < averageTest.length; i++) {
            System.out.println(averageTest[i]);
        }

        // Printing the current state of the cell
        System.out.println(test);

        // Updating the cell
        test.updateState(cells);

        // Printing the new state of the cell
        System.out.println(test);

        AICell[] aicell_arr = {cellA, cellB, cellC};
        Cell[] cell_arr = aicell_arr;

        for (int i = 0; i < cell_arr.length; i++) {
            System.out.println(cell_arr[i]);
        }


    }

}
