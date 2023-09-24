package org.vsdl.common.rlmmo.maintainable.events;

import org.junit.jupiter.api.Test;
import org.vsdl.common.engine.events.Engine;
import org.vsdl.common.rlmmo.maintainable.impl.TestChangePublicationHandler;
import org.vsdl.common.rlmmo.maintainable.impl.TestChangePublisher;
import org.vsdl.common.rlmmo.maintainable.impl.TestMaintainableFieldForChangePublication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangePublicationTest {
    @Test
    public void testChangePublication() throws InterruptedException {
        TestChangePublicationHandler testHandler = new TestChangePublicationHandler();
        Engine engine = Engine.getNewEngine(testHandler, true);
        TestMaintainableFieldForChangePublication field = new TestMaintainableFieldForChangePublication(1,1);
        field.initialize();
        engine.registerEventSource(field, 1);
        TestChangePublisher testChangePublisher = new TestChangePublisher();
        testHandler.registerChangePublisher(testChangePublisher);
        ChangePublicationScheduler scheduler = new ChangePublicationScheduler(testHandler, 125L);
        engine.start();
        scheduler.start();
        Thread.sleep(2000L);
        engine.halt();
        scheduler.halt();
        field.setRegistered(false);
        testChangePublisher.setRegistered(false);
        assertTrue(field.getVersion() > 0);
        assertEquals(field.getVersion(), testChangePublisher.getPublicationCount());
    }
}
