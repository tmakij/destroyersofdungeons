package GUI;

import actors.Actor;
import actors.Player;
import dungeon.Tunnel;
import java.util.List;
import java.util.Scanner;
import logic.DestroyersOfDungeons;

/**
 * Temporary text GUI
 */
public final class TextGUI {

    private final DestroyersOfDungeons game = new DestroyersOfDungeons();
    private final Scanner scan = new Scanner(System.in);

    /**
     * Starts the game and gui. Needs to be split up.
     */
    public final void start() {
        System.out.println("Welcome to Destroyers Of Dungeons");
        System.out.println(game.addPlayer("TestPlayer"));
        System.out.println(game.addPlayer("AnotherTestPlayer"));
        OUTER:
        while (true) {
            Player current = game.getCurrentPlayer();
            System.out.println(current + "'s turn");
            System.out.println("Please enter 0 - 1");
            String com = scan.nextLine();
            switch (com) {
                case "0":
                    break OUTER;
                case "1":
                    List<Tunnel> nextTo = game.play();
                    System.out.println("I can move to the following blocks:");
                    for (int i = 0; i < nextTo.size(); i++) {
                        System.out.println("[" + i + "]" + nextTo.get(i));
                    }
                    int tunnel;
                    Tunnel block;
                    do {
                        tunnel = scan.nextInt();
                        clearScannerBuffer(scan);
                        block = game.movePlayerTo(tunnel);
                    } while (block == null);
                    System.out.println("You have moved to " + current.getMyBlock());
                    List<Actor> others = current.getMyBlock().getOtherActors(current);
                    if (!others.isEmpty()) {
                        System.out.println("You see other person on the block:");
                        for (Actor a : others) {
                            System.out.println(a);
                        }
                        //Take actions...
                    } else {
                        System.out.println("You see nobody else on the block");
                    }
                    break;
            }
            game.nextPlayer();
        }
        System.out.println("Goodbye");
    }

    /**
     * All strings have the line terminator, which is left to the buffer when an
     * int is read. This method clears the scanner buffer.
     */
    private void clearScannerBuffer(Scanner scan) {
        scan.nextLine();
    }
}
