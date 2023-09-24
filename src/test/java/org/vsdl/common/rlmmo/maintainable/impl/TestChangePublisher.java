package org.vsdl.common.rlmmo.maintainable.impl;

import org.vsdl.common.mmo.consistency.MaintenanceTransactionRecord;
import org.vsdl.common.rlmmo.maintainable.events.ChangePublisher;

import java.util.Deque;

public class TestChangePublisher extends ChangePublisher {

    private long publicationCount = 0;

    private boolean isRegistered = false;

    @Override
    protected void doPublish(Deque<MaintenanceTransactionRecord> changes) {
        ++publicationCount;
    }

    public long getPublicationCount() {
        return publicationCount;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void setRegistered(boolean registered) {
        this.isRegistered = registered;
    }
}
