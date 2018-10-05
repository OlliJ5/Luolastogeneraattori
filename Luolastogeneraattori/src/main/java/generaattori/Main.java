
package generaattori;

import UI.Tui;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Dungeongenerator dungeon = new Dungeongenerator(150, 350);
        
        dungeon.generate(2000);
        dungeon.print();

//        Scanner scanner = new Scanner(System.in);
//        Tui tui = new Tui(scanner);
//        tui.start();
    }
    
}
