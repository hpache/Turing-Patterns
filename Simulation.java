public class Simulation {
    
    public static void main(String[] args) {
        
        // Initializing grid and display
        Landscape grid = new Landscape(200, 200);        
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
}
