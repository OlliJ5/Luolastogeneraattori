
package UI;

import generaattori.Dungeongenerator;
import java.util.Scanner;


public class Tui {
    private Scanner scanner;

    public Tui(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void start() {
        while(true) {
            System.out.println("\nPlease enter the desired height of the maze: ");
            int height = Integer.parseInt(this.scanner.nextLine());

            System.out.println("\nPlease enter the desired width of the maze: ");
            int width = Integer.parseInt(this.scanner.nextLine());

            Dungeongenerator dungeon  = new Dungeongenerator(height, width);

            System.out.println("\nHow many rooms do you want to try to place?");
            int rooms = Integer.parseInt(this.scanner.nextLine());

            dungeon.generate(rooms);
            System.out.println(dungeon.toString());;
            
            System.out.println("\n\n\nType q to quit and c to continue");
            String str = this.scanner.nextLine();
            
            if(str.equals("q")) {
                break;
            }

        }
        
        
        
    }
    
    
}
