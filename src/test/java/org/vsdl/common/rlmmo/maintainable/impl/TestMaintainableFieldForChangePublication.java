package org.vsdl.common.rlmmo.maintainable.impl;

import org.vsdl.common.engine.events.EventSource;
import org.vsdl.common.rlmmo.maintainable.world.map.MaintainableField;

public class TestMaintainableFieldForChangePublication extends MaintainableField implements EventSource {

    private boolean isRegistered = false;
    public TestMaintainableFieldForChangePublication(int rows, int columns) {
        super(rows, columns);
    }

    public void emptyChange(){}

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void setRegistered(boolean registered) {
        this.isRegistered = registered;
    }
}
