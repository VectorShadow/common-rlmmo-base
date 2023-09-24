package org.vsdl.common.rlmmo.maintainable.events;

import org.vsdl.common.engine.utils.Registrable;
import org.vsdl.common.mmo.consistency.MaintenanceTransactionRecord;

import java.util.Deque;

public interface ChangePublisher extends Registrable {

    void publish(Deque<MaintenanceTransactionRecord> changes);
}
