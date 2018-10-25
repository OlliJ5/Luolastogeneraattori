
package performance;

import generaattori.Dungeongenerator;


public class PerformanceTester {

    public PerformanceTester() {
    }
    
    public void testPerformance() {
        System.out.println("Small dungeon: (20x60)");
        this.smallDungeonTest();
        
        System.out.println("Medium dungeon: (40x120)");
        this.mediumDungeonTest();
        
        System.out.println("Medium-large dungeon: (80x240)");
        this.mediumLargeDungeon();
    }
    
    
    public void smallDungeonTest() {
        long mean = 0;
        for(int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();

            Dungeongenerator dungeon = new Dungeongenerator(20, 60, 2, 2);
            dungeon.generate(50);

            long finishTime = System.currentTimeMillis();

            long totalTime = finishTime - startingTime;
            mean += totalTime;

            System.out.println("The operation took: " + totalTime + " ms");
        }

        System.out.println("Mean " + (mean /= 10) + " ms\n");
    }
    
    public void mediumDungeonTest() {
        long mean = 0;
        for(int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();
            
            Dungeongenerator dungeon = new Dungeongenerator(40, 120, 5, 5);
            dungeon.generate(150);
            
            long finishTime = System.currentTimeMillis();
            
            long totalTime = finishTime - startingTime;
            mean += totalTime;
            
            System.out.println("The opertation took: " + totalTime + " ms" );
        }
        
        System.out.println("Mean " + (mean /= 10) + " ms\n");
    }
    
    public void mediumLargeDungeon() {
        long mean = 0;
        for(int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();
            
            Dungeongenerator dungeon = new Dungeongenerator(80, 240, 9, 9);
            dungeon.generate(500);
            
            long finishTime = System.currentTimeMillis();
            
            long totalTime = finishTime - startingTime;
            mean += totalTime;
            
            System.out.println("The operation took: " + totalTime + " ms");
        }
        
        System.out.println("Mean " + (mean /=10 ) + " ms\n");
    }
}
