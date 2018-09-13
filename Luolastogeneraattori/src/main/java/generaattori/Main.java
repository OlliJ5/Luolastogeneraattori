
package generaattori;

import UI.Tui;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon(15, 60);
        
        dungeon.generate(200);
        
        dungeon.print();
//        Scanner scanner = new Scanner(System.in);
//        Tui tui = new Tui(scanner);
//        tui.start();
    }
    
}
