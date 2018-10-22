
package generaattori;

import UI.Tui;
import java.util.Scanner;
import performance.PerformanceTester;


public class Main {


    public static void main(String[] args) {
        Dungeongenerator dungeon = new Dungeongenerator(15, 60);
        
        dungeon.generate(40);
        dungeon.print();

//        PerformanceTester tester = new PerformanceTester();
//        tester.bigDungeon();
          
//        Tui teksikayttoliittyma = new Tui(new Scanner(System.in));
//        teksikayttoliittyma.start();

    }
    
}
