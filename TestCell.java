import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class TestCell {
    
    // Declaring chemical concentration hash map
    private HashMap<String, Float> concentrations = new HashMap<>();
    // Declaring reaction parameters hash map
    private HashMap<String, Float> parameters = new HashMap<>();
    //Initializing random generator
    private Random randomGenerator = new Random();
    // Initialzing limits for our constrain method
    private float maxConcentration = 0.8f;
    private float minConcentration = 0;

    // Constructor method initializes chemical fields with random values as a default
    public TestCell(){

        // Initializing chemicals with random floats from 0 to 0.8
        this.concentrations.put("concentrationA", randomGenerator.nextFloat());
        this.concentrations.put("concentrationB", randomGenerator.nextFloat());

        // Reaction parameters set to 1 by default
        this.parameters.put("r_a", 0.001f);
        this.parameters.put("r_b", 0.15f);
        this.parameters.put("b_a", 0.05f);
        this.parameters.put("b_b", 0.05f);
        this.parameters.put("s", 0.025f);
        
    }

    // Constructor method initializes chemical fields with given values.
    // Values must be floats between 0 and 0.8
    public TestCell(float a, float b){

        // If one of the concentrations is greater than 0.8 or less than 0 then
        // concentrations will be constrained!
        if ((a > 1 || b > 1) || (a < 0 || b < 0)){
            
            // constrain values that are not between 0 and 1
            a = this.limit(a);
            b = this.limit(b);

            this.concentrations.put("concentrationA", a);
            this.concentrations.put("concentrationB", b);
        }
        else{

            // Initializing chemicals with given parameters
            this.concentrations.put("concentrationA", a);
            this.concentrations.put("concentrationB", b);
        }

        // Reaction parameters set to 1 by default
        this.parameters.put("r_a", 1f);
        this.parameters.put("r_b", 1f);
        this.parameters.put("b_a", 1f);
        this.parameters.put("b_b", 1f);
        this.parameters.put("s", 1f);
    }

    // This method resets all the concentrations to random floats from 0 to 0.8
    public void reset(){
    
        this.concentrations.put("concentrationA", randomGenerator.nextFloat());
        this.concentrations.put("concentrationB", randomGenerator.nextFloat());
    }

    // This method returns a constrained value if it is not between 0 and 0.8.
    // value < 0 returns 0
    // value > 0.8 returns 0.8
    private float limit(float value){

        // Constrain using min and max methods
        value = Math.min( Math.max(value, this.minConcentration), this.maxConcentration);

        return value;
    }

    // This method returns the concentration of chemicals a,b,c in the cell as an array list
    // The returned arraylist is of the form [concentrationA, concentrationB, concentrationC]
    public ArrayList<Float> getConcentrations(){
        
        // Allocating concentration ArrayList
        ArrayList<Float> concentrations = new ArrayList<Float>();

        // Assigning concentrations to ArrayList indices
        concentrations.add(this.concentrations.get("concentrationA"));
        concentrations.add(this.concentrations.get("concentrationB"));

        return concentrations;
    }

    // Method sets paramets
    public void setParameters(HashMap<String, Float> newParameters) { 
        this.parameters = newParameters; 
    }

    // This method sets the concentrations of the chemicals
    // Must be a float with value between 0 and 0.8
    public void setConcentrations(HashMap<String, Float> newConcentrations){

        Float a = newConcentrations.get("a");
        Float b = newConcentrations.get("b");

        // If one of the concentrations is not between 0 or 0.8 then constrain it
        if ((a > 1 || b > 1) || (a < 0 || b < 0)){
            
            // Constraining chemicals not between 0 or 1
            a = this.limit(a);
            b = this.limit(b);

            this.concentrations.put("concentrationA", a);
            this.concentrations.put("concentrationB", b);
        }
        else{

            // Setting concentrations to given parameters 
            this.concentrations.put("concentrationA", a);
            this.concentrations.put("concentrationB", b);
        }

    }

    // Method returns the parameters hash map
    public HashMap<String, Float> getParameters() { return this.parameters; }

    // This method draws the cell. The color of the cell is set
    // Using the HSB model which is why concentrations must be floats between 0 and 0.8
    // Currently the color changes as a function of concentration A, but this 
    // can be changed to any concentration.
    public void draw(Graphics g, int x, int y, int scale){

        // Draw an oval representing a cell at position x,y, scaled by scale
        g.drawRect(x, y, scale, scale);

        // Setting the color in accordance to concentration A
        // Using HSB model of color
        Color cellColor = Color.getHSBColor(this.concentrations.get("concentrationB"), 1.0f, 1.0f);
        g.setColor(cellColor);

        // Fill oval object at x,y with current collor 
        g.fillRect(x, y, scale, scale);
    }

    // This method returns an array with  the average concentration
    // of the neighboring cells including the current cell
    private float[] average(ArrayList<TestCell> neighbors){

        // Initializing variables for the total concentration of each chemical
        float totalConcentrationA = 0;
        float totalConcentrationB = 0;

        // Iterating over the neighbors
        for (int i = 0; i < neighbors.size(); i++){

            // Get the cell from the neighbors
            TestCell currentCell = neighbors.get(i);

            // Get concentration from the currentCell
            ArrayList<Float> cellConcentration = currentCell.getConcentrations();

            // Adding concentrations to appropiate variables
            totalConcentrationA += cellConcentration.get(0);
            totalConcentrationB += cellConcentration.get(1);
        }

        // Average concentrations over 9 cells
        float averageConcentrationA = totalConcentrationA / 9;
        float averageConcentrationB = totalConcentrationB / 9;

        // constrain the averages
        averageConcentrationA = this.limit(averageConcentrationA);
        averageConcentrationB = this.limit(averageConcentrationB);

        // Allocating array with average concentrations
        float[] averages = {averageConcentrationA, averageConcentrationB};

        return averages;
    }

    // This method calculates new concentrations based on the
    // BZ reaction. It then outputs the new concentrations in an array of the form {A,B,C}.
    // The input is a neighbors ArrayList since it calls on the average method.
    private float[] BZ(ArrayList<TestCell> neighbors){

        // Finds average concentrations by calling on the average method
        float[] concentrations = this.average(neighbors);

        // Getting concentrations from the output of the average method
        float A = concentrations[0];
        float B = concentrations[1];

        float r_a = this.parameters.get("r_a");
        float r_b = this.parameters.get("r_b");
        float b_a = this.parameters.get("b_a");
        float b_b = this.parameters.get("b_b");
        float s = this.parameters.get("s");

        // Calculating new concentrations 
        float newA = A - r_a * A + s * ((A * A)/B + b_a);
        float newB = B - r_b * B + s * A * A + b_b;

        // Allocating array with new concentrations
        float[] newConcentrations = {newA, newB};

        return newConcentrations;
    }

    // This method updates the state of the turing cell. 
    public void updateState(ArrayList<TestCell> neighbors){

        // Calculating new concentrations based on the neighbors
        float[] newConcentration = this.BZ(neighbors);

        // Assign calculated concentrations to cell's concentration
        this.concentrations.replace("concentrationA", newConcentration[0]);
        this.concentrations.replace("concentrationB",newConcentration[1]);
    }

    public String toString(){

        // The cell will be represented in string format as the concentration of chemical A
        String output = "%s";
        output = String.format(output, this.concentrations.get("concentrationA"));

        return output;
    }
}
