package GUI;

import logic.IUpdate;

public final class TestGUIPanel implements IUpdate {

    private boolean updated = false;

    @Override
    public void update() {
        updated = true;
    }

    public boolean isUpdated() {
        return updated;
    }
}
