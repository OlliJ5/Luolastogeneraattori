
package generaattori;

import UI.Tui;
import java.util.Scanner;
import performance.PerformanceTester;


public class Main {


    public static void main(String[] args) {
        Dungeongenerator dungeon = new Dungeongenerator(60, 240);
        
        dungeon.generate(200);
        dungeon.print();

//        PerformanceTester tester = new PerformanceTester();
//        tester.bigDungeon();
          
//        Tui teksikayttoliittyma = new Tui(new Scanner(System.in));
//        teksikayttoliittyma.start();

    }
    
}
