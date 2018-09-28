
package generaattori;

import UI.Tui;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Dungeongenerator dungeon = new Dungeongenerator(7, 30);
        
        dungeon.generate(8);
        //dungeon.createPassageways();
        
        //dungeon.printRooms();
        
        dungeon.print();
        dungeon.printConnectors();
//        Scanner scanner = new Scanner(System.in);
//        Tui tui = new Tui(scanner);
//        tui.start();
    }
    
}
