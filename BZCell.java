/**
 * Henry Pacheco Cachon
 * Created: 5 March 2022
 * File is a BZ cell which is a child of the Cell class
 */

 import java.util.HashMap;
 import java.util.ArrayList;
 import java.awt.Graphics;
 import java.awt.Color;

public class BZCell extends Cell {
    
    // Default constructor don't do anything 
    public BZCell(){
        super();
    }

    // Constructor with custom concentrations
    public BZCell(HashMap<String, Float> concentrations){

        // Initialize parent class
        super();
        
        // Create model concentrations hashmap 
        HashMap<String, Float> modelConcentrations = new HashMap<>();
        // Create model parameters hashmap 
        HashMap<String, Float> modelParameters = new HashMap<>();

        // Getting input parameters
        float a = concentrations.get("a");
        float b = concentrations.get("b");
        float c = concentrations.get("c");

        // If one of the concentrations is greater than 0.8 or less than 0 then
        // concentrations will be constrained!
        if ((a > 1 || b > 1 || c > 1) || (a < 0 || b < 0 || c < 0)){
            
            // constrain values that are not between 0 and 1
            a = this.limit(a);
            b = this.limit(b);
            c = this.limit(c);

            modelConcentrations.put("a", a);
            modelConcentrations.put("b", b);
            modelConcentrations.put("c", c);
        }
        else{

            // Initializing chemicals with given parameters
            modelConcentrations.put("a", a);
            modelConcentrations.put("b", b);
            modelConcentrations.put("c", c);
        }

        // Reaction parameters set to 1 by default
        modelParameters.put("alpha", 1f);
        modelParameters.put("beta", 1f);
        modelParameters.put("gamma", 1f);

        // Setting model concentrations
        this.setConcentrations(modelConcentrations);
        // Setting model parameters
        this.setParameters(modelParameters);
    }

    // Method resets the models concentrations only!
    public void reset(){

        // Create new concentrations hashmap
        HashMap<String, Float> resetConcentrations = new HashMap<>();

        resetConcentrations.put("a", this.randGen.nextFloat());
        resetConcentrations.put("b", this.randGen.nextFloat());
        resetConcentrations.put("c", this.randGen.nextFloat());

        this.setConcentrations(resetConcentrations);

    }


    // Method calculates the cells new concentrations based on 
    // the concentrations of its neighboring cells
    private float[] BZ(ArrayList<Cell> neighbors){

        float[] concentrations = this.average(neighbors);

        // Getting concentrations from the output of the average method
        float A = concentrations[0];
        float B = concentrations[1];
        float C = concentrations[2];

        HashMap<String, Float> parameters = this.getParameters();

        float alpha = parameters.get("alpha");
        float beta = parameters.get("beta");
        float gamma = parameters.get("gamma");

        // Calculating new concentrations 
        float newA = A + A * (alpha * B - gamma * C);
        float newB = B + B * (beta * C - alpha * A);
        float newC = C + C * (gamma * A - beta * B);

        // Allocating array with new concentrations
        float[] newConcentrations = {newA, newB, newC};

        return newConcentrations;
    }

    
    // Method updates the state of the current cell
    public void updateState(ArrayList<Cell> neighbors){

        // Getting the new concentrations
        float[] newConcentrations = this.BZ(neighbors);

        // Creating a hashmap for the updated concentrations
        HashMap<String, Float> updateConcentrations = new HashMap<>();
        updateConcentrations.put("a", newConcentrations[0]);
        updateConcentrations.put("b", newConcentrations[1]);
        updateConcentrations.put("c", newConcentrations[2]);

        // Updating the concentrations hashmap field
        this.setConcentrations(updateConcentrations);
    }

    
    // Draw method for BZCell
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
        concentrations.put("c", 0.1f);

        // Creating a test BZCell object
        BZCell test = new BZCell(concentrations);

        // Outputting the concentrations
        System.out.println(test.getConcentrations());

        // Resetting the cell
        test.reset();

        // Outputting the reset concentrations 
        System.out.println(test.getConcentrations());

        // Creating three BZ cells
        BZCell cellA = new BZCell();
        BZCell cellB = new BZCell();
        BZCell cellC = new BZCell();

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

    }
}
