
package performance;

import generaattori.Dungeongenerator;


public class PerformanceTester {

    public PerformanceTester() {
    }
    
    public void testPerformance() {
        System.out.println("Pieni luolasto:");
        this.smallDungeonTest();
        
        System.out.println("Keskikokoinen luolasto:");
        this.mediumDungeonTest();
        
        System.out.println("Iso luolasto:");
        this.bigDungeon();
    }
    
    
    public void smallDungeonTest() {
        for(int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();

            Dungeongenerator dungeon = new Dungeongenerator(20, 60);
            dungeon.generate(50);

            long finishTime = System.currentTimeMillis();

            long totalTime = finishTime - startingTime;

            System.out.println("Operaatioon kului: " + totalTime + " ms");
        }
        System.out.println("");
    }
    
    public void mediumDungeonTest() {
        for(int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();
            
            Dungeongenerator dungeon = new Dungeongenerator(40, 120);
            dungeon.generate(150);
            
            long finishTime = System.currentTimeMillis();
            
            long totalTime = finishTime - startingTime;
            
            System.out.println("Operaatioon kului: " + totalTime + " ms" );
        }
        System.out.println("");
    }
    
    public void bigDungeon() {
        for(int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();
            
            Dungeongenerator dungeon = new Dungeongenerator(80, 240);
            dungeon.generate(500);
            
            long finishTime = System.currentTimeMillis();
            
            long totalTime = finishTime - startingTime;
            
            System.out.println("Operaatioon kului: " + totalTime + " ms");
        }
        System.out.println("");
    }
}
