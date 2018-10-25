package UI;

import generaattori.Dungeongenerator;
import java.util.Scanner;
import performance.PerformanceTester;

public class Tui {

    private Scanner scanner;
    private int height;
    private int width;
    private int minRoomHeight;
    private int minRoomWidth;
    private int roomAttempts;

    public Tui(Scanner scanner) {
        this.scanner = scanner;
        this.width = 60;
        this.height = 15;

        this.minRoomHeight = 2;
        this.minRoomWidth = 2;

        this.roomAttempts = 6;
    }

    public void start() {
        System.out.println("Welcome!");
        while (true) {
            System.out.println(" \n'r' to run the generator \nType 's' for settings"
                    + "\n'h' for help"
                    + "\n'p' for performance check"
                    + "\n'd' for a demo on how the algorithm works and"
                    + "\n'q' to quit");

            String command = scanner.nextLine();

            if (command.equals("s")) {
                this.settings();
            } else if (command.equals("r")) {
                Dungeongenerator dungeon = new Dungeongenerator(height, width, minRoomHeight, minRoomWidth);
                dungeon.generate(roomAttempts);
                System.out.println(dungeon.toString());
            } else if (command.equals("h")) {
                this.help();
            } else if (command.equals("q")) {
                break;
            } else if (command.equals("p")) {
                this.performance();
            } else if (command.equals("d")) {
                this.demo();
            } else {
                System.out.println(command + " is an unknown command! :(");
            }
        }

        System.out.println("Thank you, come again");

    }

    public void help() {
        System.out.println("This is the help page!\n"
                + "With this application you can generate dungeons\n"
                + "The default size of the dungeon is 15x60 with 6 attempts "
                + "to place a room but you can also create much larger dungeons.\n"
                + "To change the size and the amount of rooms attempted to place, "
                + "go to the settings page. Enjoy");
    }

    public void performance() {
        PerformanceTester tester = new PerformanceTester();
        tester.testPerformance();
    }

    public void demo() {
        Dungeongenerator generator = new Dungeongenerator(15, 60, 2, 2);

        System.out.println("First the dungeon looks like this:");
        System.out.println(generator.toString());

        System.out.println("\nPress enter to continue");
        String command = scanner.nextLine();

        System.out.println("\n\nThen we place some rooms");
        generator.placeRooms(7);
        System.out.println(generator.toString());

        System.out.println("\nPress enter to continue");
        command = scanner.nextLine();

        System.out.println("\n\nThen we fill the dungeon with corridors");
        generator.buildCorridors();
        System.out.println(generator.toString());

        System.out.println("\nPress enter to continue");
        command = scanner.nextLine();

        System.out.println("\n\nThen we connect the dungeon");
        generator.connectDungeon();
        System.out.println(generator.toString());

        System.out.println("\nPress enter to continue");
        command = scanner.nextLine();

        System.out.println("\n\nThen we remove the dead ends form the corridors");
        generator.removeDeadEnds();
        System.out.println(generator.toString());

        System.out.println("\nPress enter to continue");
        command = scanner.nextLine();

        System.out.println("\n\nAnd finally we make it pretty");
        generator.makeItPretty();
        System.out.println(generator.toString());

    }

    public void settings() {

        while (true) {
            System.out.println("Current size is " + this.height + "x" + this.width
                    + "\nCurrent amount of attempts to place a room is " + this.roomAttempts
                    + "\nMinimum room size is " + this.minRoomHeight + "x" + this.minRoomWidth);
            System.out.println("\nDo you want to change these settings? (y/n)");

            String command = scanner.nextLine();

            if (command.equals("n")) {
                return;
            } else if (command.equals("y")) {
                break;
            } else {
                System.out.println(command + " is an unknown command! :(\n");
            }

        }

        int height;
        do {
            System.out.println("Please enter the desired height of the maze\n"
                    + "(min 10 and max 500)");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            height = scanner.nextInt();
        } while (height < 10 || height > 500);

        this.height = height;
        scanner.nextLine();

        int width;
        do {
            System.out.println("Please enter the desired width of the maze\n"
                    + "(min 10 and max 500)");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            width = scanner.nextInt();
        } while (width < 10 || width > 500);

        this.width = width;
        scanner.nextLine();

        int attempts;
        do {
            System.out.println("How many rooms do you want to attempt to place\n"
                    + "(min 0 and max 10000)");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            attempts = scanner.nextInt();
        } while (attempts < 0 || attempts > 10000);

        this.roomAttempts = attempts;
        scanner.nextLine();

        int roomHeight;
        do {
            System.out.println("Please enter the desired minumum height of the rooms\n"
                    + "(Min 2 and max a quarter of the dungeon height)");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            roomHeight = scanner.nextInt();
        } while (roomHeight < 2 || roomHeight > this.height / 4);

        this.minRoomHeight = roomHeight;
        scanner.nextLine();

        int roomWidth;
        do {
            System.out.println("Please enter the desired minimum width of the rooms\n"
                    + "(Min 2 and max a quarter of the dungeon width)");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            roomWidth = scanner.nextInt();
        } while (roomWidth < 2 || roomWidth > this.width / 4);

        this.minRoomWidth = roomWidth;
        scanner.nextLine();

    }

}
