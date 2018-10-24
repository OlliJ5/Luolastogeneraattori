package UI;

import generaattori.Dungeongenerator;
import java.util.Scanner;

public class Tui {

    private Scanner scanner;
    private int height;
    private int width;
    private int roomAttempts;

    public Tui(Scanner scanner) {
        this.scanner = scanner;
        this.width = 60;
        this.height = 15;
        this.roomAttempts = 6;
    }

    public void start() {
        System.out.println("Welcome!");
        while (true) {
            System.out.println(" \nType 's' for settings \n'r' to run the generator \n'h' for help \nand 'q' to quit\n");

            String command = scanner.nextLine();

            if (command.equals("s")) {
                this.settings();
            } else if (command.equals("r")) {
                Dungeongenerator dungeon = new Dungeongenerator(height, width);
                dungeon.generate(roomAttempts);
                System.out.println(dungeon.toString());
            } else if (command.equals("h")) {
                this.help();
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println(command + " is an unknown command! :(");
            }
        }

        System.out.println("Thank you, come again");

    }

    public void settings() {

        while (true) {
            System.out.println("Current size is " + this.height + "x" + this.width
                    + "\nCurrent amount of attempts to place a room is " + this.roomAttempts);
            System.out.println("\nDo you want to change these settings? (y/n)");
            
            String command = scanner.nextLine();

            if (command.equals("n")) {
                return;
            } else if (command.equals("y")) {
                break;
            } else {
                System.out.println(command + " is an unknown command! :(");
            }

        }

        int height;
        do {
            System.out.println("Please enter the desired height of the maze (min 10)");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            height = scanner.nextInt();
        } while (height < 10 || height > 500); 
        
        this.height = height;
        scanner.nextLine();
        
        int width;
        do {
            System.out.println("Please enter the desired width of the maze (min 10)");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            width = scanner.nextInt();
        } while (width < 10 || width > 500);
        
        this.width = width;
        scanner.nextLine();
        
        int attempts;
        do {
            System.out.println("How many rooms do you want to attempt to place (min 0)");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            attempts = scanner.nextInt();
        } while (attempts < 0 || attempts > 10000);

        this.roomAttempts = attempts;
        scanner.nextLine();
        
    }

    public void help() {
        System.out.println("This is the help page!\n"
                + "With this application you can generate dungeons\n"
                + "The default size of the dungeon is 15x60 with 6 attempts "
                + "to place a room but you can also create much larger dungeons.\n"
                + "To change the size and the amount of rooms attempted to place, "
                + "go to the settings page. Enjoy");
    }

}
