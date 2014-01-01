package GUI.panels;

/**
 * Interface for GUI panels. It is used by battles to update the gui acordingly
 * as the battle progress.
 */
public interface IUpdate {

    /**
     * Call whenever the gui needs to be updated to reflect the changes in the
     * battle.
     */
    public void update();
}
