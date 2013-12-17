package GUI;

import java.util.Scanner;
import logic.DestroyersOfDungeons;

/**
 * Temporary text GUI
 */
public final class TextGUI {

    private final DestroyersOfDungeons game = new DestroyersOfDungeons();

    public final void start() {
        System.out.println("Welcome to Destroyers Of Dungeons");
        System.out.println(game.addPlayer("TestPlayer"));
        System.out.println(game.addPlayer("AnotherTestPlayer"));
        Scanner scan = new Scanner(System.in);
        int turn = 0;
        OUTER:
        while (true) {
            System.out.println("Please enter 1 - 3, Empty line quits");
            String com = scan.nextLine();
            switch (com) {
                case "":
                    break OUTER;
                case "1":
                    String moving = game.play(turn);
                    int tunnel;
                    String result;
                    do {
                        System.out.println(moving);
                        tunnel = scan.nextInt();
                        System.out.println("scanned " + tunnel);
                        
                        clearScannerBuffer(scan);

                        result = game.movePlayerTo(turn, tunnel);
                    } while (!result.isEmpty());
                    System.out.println(result);
                    break;
            }
            turn = turn == 0 ? 1 : 0;
            System.out.println("Now is player " + turn + "'s turn");
        }
        System.out.println("Goodbye");
    }

    private void clearScannerBuffer(Scanner scan) {
        scan.nextLine();
    }
}
