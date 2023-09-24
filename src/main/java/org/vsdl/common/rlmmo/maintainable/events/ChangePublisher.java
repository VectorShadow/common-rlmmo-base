package org.vsdl.common.rlmmo.maintainable.events;

import org.vsdl.common.engine.utils.Registrable;
import org.vsdl.common.mmo.consistency.MaintenanceTransactionRecord;

import java.util.Deque;

public abstract class ChangePublisher implements Registrable {

    public void publish(Deque<MaintenanceTransactionRecord> changes){
        Thread t = new Thread(() -> doPublish(changes));
        t.start();
    }

    protected abstract void doPublish(Deque<MaintenanceTransactionRecord> changes);
}
