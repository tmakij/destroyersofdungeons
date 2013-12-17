package GUI;

import java.util.Scanner;
import logic.DestroyersOfDungeons;

/**
 * Temporary text GUI
 */
public final class TextGUI {

    private final DestroyersOfDungeons game = new DestroyersOfDungeons();
    private final Scanner scan = new Scanner(System.in);

    public final void start() {
        System.out.println("Welcome to Destroyers Of Dungeons");
        System.out.println(game.addPlayer("TestPlayer"));
        System.out.println(game.addPlayer("AnotherTestPlayer"));
        game.checkHostilities();
        OUTER:
        while (true) {
            System.out.println("Please enter 0 - 1");
            String com = scan.nextLine();
            switch (com) {
                case "0":
                    break OUTER;
                case "1":
                    String moving = game.play();
                    int tunnel;
                    String result;
                    do {
                        System.out.println(moving);
                        tunnel = scan.nextInt();
                        clearScannerBuffer(scan);
                        result = game.movePlayerTo(tunnel);
                    } while (result.isEmpty());
                    System.out.println(result);
                    break;
            }
            System.out.println(game.nextPlayer());
        }
        System.out.println("Goodbye");
    }

    private void clearScannerBuffer(Scanner scan) {
        scan.nextLine();
    }
}
