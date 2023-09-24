package org.vsdl.common.rlmmo.maintainable.impl;

import org.vsdl.common.engine.events.EngineEvent;
import org.vsdl.common.mmo.consistency.MaintenanceTransaction;
import org.vsdl.common.mmo.consistency.MaintenanceTransactionRecord;
import org.vsdl.common.rlmmo.maintainable.events.ChangePublicationHandler;

import static org.junit.jupiter.api.Assertions.fail;

public class TestChangePublicationHandler extends ChangePublicationHandler {
    @Override
    public EngineEvent handle(EngineEvent engineEvent, long l) {
        TestMaintainableFieldForChangePublication field = (TestMaintainableFieldForChangePublication) engineEvent.getSource();
        MaintenanceTransactionRecord record = MaintenanceTransactionRecord.initializeRecord(field.getUUID(), field.getVersion());
        MaintenanceTransaction transaction = new MaintenanceTransaction(TestMaintainableFieldForChangePublication.class.getCanonicalName(), "emptyChange", new Class[]{}, new Object[]{});
        record.record(transaction);
        try {
            record.applyTo(field);
        } catch (Exception e) {
            fail();
        }
        alterLog(record);
        return new EngineEvent(l + 125L, field);
    }
}
