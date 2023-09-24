package org.vsdl.common.rlmmo.maintainable.events;

import org.vsdl.common.engine.events.EventHandler;
import org.vsdl.common.mmo.consistency.MaintenanceTransactionRecord;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class ChangePublicationHandler implements EventHandler {
    private Deque<MaintenanceTransactionRecord> changeLog;

    private final List<ChangePublisher> changePublishers = new ArrayList<>();

    private void resetChangeLog() {
        changeLog = new ArrayDeque<>();
    }

    public synchronized void alterLog(MaintenanceTransactionRecord change) {
        if (nonNull(change)) {
            changeLog.addLast(change);
        } else {
            for (ChangePublisher changePublisher : changePublishers) {
                if (!changePublisher.isRegistered()) {
                    changePublishers.remove(changePublisher);
                } else {
                    changePublisher.publish(changeLog);
                }
            }
        }
        resetChangeLog();
    }

    public void registerChangePublisher(ChangePublisher changePublisher) {
        changePublisher.setRegistered(true);
        changePublishers.add(changePublisher);
    }

}
