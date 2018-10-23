
package generaattori;

import UI.Tui;
import java.util.Scanner;
import performance.PerformanceTester;


public class Main {


    public static void main(String[] args) {
//        Dungeongenerator dungeon = new Dungeongenerator(490, 1490);
//        
//        dungeon.generate(2000);
//        dungeon.print();

//        PerformanceTester tester = new PerformanceTester();
//        tester.bigDungeon();
          
        Tui teksikayttoliittyma = new Tui(new Scanner(System.in));
        teksikayttoliittyma.start();

    }
    
}
