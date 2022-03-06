/**
 * Henry Pacheco Cachon
 * Created: 28 February 2022
 * This class is the main cell class. Using inheritance, I will
 * make other cell-like classes that will have specific implementations for
 * their respective models
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;

// Data class, only have the most basic thing!
public class Cell {
    
    // concentrations hashmap
    private HashMap<String, Float> concentrations = new HashMap<>();
    // Parameters hashmap
    private HashMap<String, Float> parameters = new HashMap<>();

    // Random generator
    public Random randGen = new Random();

    // Max and min concentrations
    private float maxConcentration = 0.8f;
    private float minConcentration = 0.0f;

    public Cell(){

        // Initializing chemicals with random floats from 0 to 0.8
        this.concentrations.put("a", randGen.nextFloat());
        this.concentrations.put("b", randGen.nextFloat());
        this.concentrations.put("c", randGen.nextFloat());

        // Initializing default parameters to 1 by default
        this.parameters.put("alpha", 1f);
        this.parameters.put("beta", 1f);
        this.parameters.put("gamma", 1f);
    }


    // Get concentrations
    public HashMap<String,Float> getConcentrations() {return this.concentrations;}


    // Get parameters
    public HashMap<String, Float> getParameters() {return this.parameters;}


    // Set concentrations
    public void setConcentrations(HashMap<String, Float> newConcentrations){
        this.concentrations = newConcentrations;
    }


    // Set parameters
    public void setParameters(HashMap<String, Float> newParameters){
        this.parameters = newParameters;
    }


    // Limits the input value to minConcentration or maxConcentration
    public float limit(float value){
        
        return Math.min( Math.max(value, this.minConcentration), this.maxConcentration);
    }

    // Finds the average concentrations
    public float[] average(ArrayList<Cell> neighbors){

        // Get the number of cells in the neighbors arraylist
        int numCells = neighbors.size();
        // Get a sample cell
        Cell sample = neighbors.get(0);
        // Get sample concentrations
        HashMap<String, Float> sampleConcentrations = sample.getConcentrations();
        // Get number of concentrations
        int numConcentrations = sampleConcentrations.size();
        float[] averageConcentrations = new float[numConcentrations];

        // Iterate through each cell
        for (Cell cell : neighbors) {
            
            // Getting current cell's concentrations
            ArrayList<Float> currentConcentrations = new ArrayList<>(cell.getConcentrations().values());

            // Iterating through each concentration
            for (int i = 0; i < averageConcentrations.length; i++) {
                // Adding it to the average concentration array
                averageConcentrations[i] += currentConcentrations.get(i);
            }
        }

        for (int i = 0; i < averageConcentrations.length; i++) {
            averageConcentrations[i] = averageConcentrations[i] / numCells;
            averageConcentrations[i] = this.limit(averageConcentrations[i]);
        }

        return averageConcentrations;
    }

    // Update state method do nothing for this class
    public void updateState(ArrayList<Cell> neighbors) {}

    // Draw method do nothing for this class
    public void draw(Graphics g, int x, int y, int scale) {}

    // Reset method do nothing for this class
    public void reset() {}

    // Overrides the inherited toString method, only shows concentration A
    public String toString(){

        String output = "";
        Float concentrationA = this.getConcentrations().get("a");
        output += concentrationA;
        return output;
    }

}
