
package generaattori;

import UI.Tui;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Dungeongenerator dungeon = new Dungeongenerator(15, 60);
        
        dungeon.generate(10);
        //dungeon.createPassageways();
        
        //dungeon.printRooms();
        
        dungeon.print();
//        Scanner scanner = new Scanner(System.in);
//        Tui tui = new Tui(scanner);
//        tui.start();
    }
    
}
