
package generaattori;

import UI.Tui;
import java.util.Scanner;
import performance.PerformanceTester;


public class Main {


    public static void main(String[] args) {
//        Dungeongenerator dungeon = new Dungeongenerator(30, 120, 5, 5);
//        
//        dungeon.generate(30);
//        System.out.println(dungeon.toString());

//        PerformanceTester tester = new PerformanceTester();
//        tester.bigDungeon();
          
        Tui teksikayttoliittyma = new Tui(new Scanner(System.in));
        teksikayttoliittyma.start();

    }
    
}
