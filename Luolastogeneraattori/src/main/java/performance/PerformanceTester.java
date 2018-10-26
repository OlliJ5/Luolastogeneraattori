package performance;

import generaattori.Dungeongenerator;

/**
 *
 * @author ogrousu
 */
public class PerformanceTester {

    /**
     * Creates an Object that tests the algorithm's performance
     */
    public PerformanceTester() {
    }

    /**
     * Tests the algorithms performance
     */
    public void testPerformance() {
        System.out.println("Small dungeon: (20x60)");
        this.smallDungeonTest();

        System.out.println("Medium dungeon: (40x120)");
        this.mediumDungeonTest();

        System.out.println("Medium-large dungeon: (80x240)");
        this.mediumLargeDungeon();

        System.out.println("Large dungeon: (500x500)");
        this.largeDungeon();
    }

    /**
     * Tests the performance of a small dungeon (size 20x60) with 50 attempts to
     * place a room
     */
    public void smallDungeonTest() {
        long mean = 0;
        for (int i = 0; i < 10; i++) {
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

    /**
     * Tests the performance of a medium dungeon (size 40x120) with 150 attempts
     * to place a room
     */
    public void mediumDungeonTest() {
        long mean = 0;
        for (int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();

            Dungeongenerator dungeon = new Dungeongenerator(40, 120, 5, 5);
            dungeon.generate(150);

            long finishTime = System.currentTimeMillis();

            long totalTime = finishTime - startingTime;
            mean += totalTime;

            System.out.println("The opertation took: " + totalTime + " ms");
        }

        System.out.println("Mean " + (mean /= 10) + " ms\n");
    }

    /**
     * Tests the performance of a medium large dungeon (size 80x240) with 500
     * attempts to place a room
     */
    public void mediumLargeDungeon() {
        long mean = 0;
        for (int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();

            Dungeongenerator dungeon = new Dungeongenerator(80, 240, 9, 9);
            dungeon.generate(500);

            long finishTime = System.currentTimeMillis();

            long totalTime = finishTime - startingTime;
            mean += totalTime;

            System.out.println("The operation took: " + totalTime + " ms");
        }

        System.out.println("Mean " + (mean /= 10) + " ms\n");
    }

    /**
     * Tests the performance of a large dungeon (size 500x500) with 1000
     * attempts to place a room
     */
    public void largeDungeon() {
        long mean = 0;
        for (int i = 0; i < 10; i++) {
            long startingTime = System.currentTimeMillis();

            Dungeongenerator dungeon = new Dungeongenerator(500, 500, 10, 21);
            dungeon.generate(1000);

            long finishTime = System.currentTimeMillis();

            long totalTime = finishTime - startingTime;
            mean += totalTime;

            System.out.println("The operation took: " + totalTime + " ms");
        }

        System.out.println("Mean " + (mean /= 10) + " ms\n");
    }

}
