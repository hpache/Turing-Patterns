/**
 * Henry Pacheco Cachon
 * Created 6 March 2022
 * This class is responsible for running the simulation for turing pattern generation
 */

import Models.*;
import java.util.HashMap;
import Display.*;

public class Simulation {

    private Model model_type;

    public Simulation(Model model){
        this.model_type = model;

        this.runSimulation();
    }

    public Simulation(Model model, HashMap<String, Float> parameters){
        this.model_type = model;

        this.runSimulation(parameters);
    }

    private void runSimulation(){

        // Initializing grid and display
        Landscape grid = new Landscape(200, 200, this.model_type);        
        LandscapeDisplay display1 = new LandscapeDisplay(grid, 4);

        // Simulating 200 iterations, a pattern should appear at the 20th iteration
        for (int i = 0; i < 200; i++){
            grid.advance();
            display1.repaint();
            try {
                Thread.sleep(25);
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }


    private void runSimulation(HashMap<String, Float> parameters){
        // Initializing grid and display
        
        Landscape grid = new Landscape(200, 200, this.model_type, "parameters", parameters);        
        LandscapeDisplay display1 = new LandscapeDisplay(grid, 4);

        // Simulating 200 iterations, a pattern should appear at the 20th iteration
        for (int i = 0; i < 200; i++){
            grid.advance();
            display1.repaint();
            try {
                Thread.sleep(25);
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
    
    
    // Method overrides the inherited toString method 
    public String toString(){
        return "Finished modeling the " + this.model_type;
    }
    
    public static void main(String[] args) {
     
        if (args.length == 0 ){
            Simulation defaultCase = new Simulation(new BZModel());
            System.out.println(defaultCase);
        }

        if (args.length == 1){
            Float gamma = Float.parseFloat(args[0]);

            HashMap<String, Float> parameters = new HashMap<>();
            parameters.put("gamma", gamma);

            Simulation parametersCase = new Simulation(new AIModel(), parameters);
            System.out.println(parametersCase);
        }
        
        if (args.length == 3){

            Float a = Float.parseFloat(args[0]);
            Float b = Float.parseFloat(args[1]);
            Float g = Float.parseFloat(args[2]);
        
            HashMap<String, Float> parameters = new HashMap<>();
            
            parameters.put("alpha", a);
            parameters.put("beta",b);
            parameters.put("gamma",g);

            Simulation parametersCase = new Simulation(new BZModel(), parameters);
            System.out.println(parametersCase);
        }

    }
}
