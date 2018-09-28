
package UI;

import generaattori.Dungeongenerator;
import java.util.Scanner;


public class Tui {
    private Scanner scanner;

    public Tui(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void start() {
        System.out.print("Please enter the desired height of the maze: ");
        int height = Integer.parseInt(this.scanner.nextLine());
        
        System.out.print("Please enter the desired width of the maze: ");
        int width = Integer.parseInt(this.scanner.nextLine());
        
        Dungeongenerator dungeon  = new Dungeongenerator(height, width);
        dungeon.placeRooms(40);
        dungeon.print();
        
        
    }
    
    
}
