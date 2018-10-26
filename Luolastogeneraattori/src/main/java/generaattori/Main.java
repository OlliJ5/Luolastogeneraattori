package generaattori;

import UI.Tui;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Tui teksikayttoliittyma = new Tui(new Scanner(System.in));
        teksikayttoliittyma.start();

    }

}
