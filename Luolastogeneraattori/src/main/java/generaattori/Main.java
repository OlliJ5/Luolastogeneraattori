
package generaattori;

import UI.Tui;
import java.util.Scanner;
import performance.PerformanceTester;


public class Main {


    public static void main(String[] args) {
//        Dungeongenerator dungeon = new Dungeongenerator(15, 60, 2, 2);
//        
//        dungeon.generate(7);
//        System.out.println(dungeon.toString());

//        PerformanceTester tester = new PerformanceTester();
//        tester.smallDungeonTest();
          
        Tui teksikayttoliittyma = new Tui(new Scanner(System.in));
        teksikayttoliittyma.start();

    }
    
}
