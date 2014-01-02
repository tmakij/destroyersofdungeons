package GUI;

import logic.IUpdate;

public final class testGUIPanel implements IUpdate {

    private boolean updated = false;

    @Override
    public void update() {
        updated = true;
    }

    public boolean isUpdated() {
        return updated;
    }
}
