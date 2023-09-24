package org.vsdl.common.rlmmo.maintainable.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vsdl.common.mmo.consistency.MaintenanceTransaction;
import org.vsdl.common.mmo.consistency.MaintenanceTransactionRecord;
import org.vsdl.common.rl.world.asset.Actor;
import org.vsdl.common.rl.world.asset.Feature;
import org.vsdl.common.rl.world.map.Field;
import org.vsdl.common.rl.world.map.MapLoc;
import org.vsdl.common.rlmmo.maintainable.fixtures.MaintainableFixture;
import org.vsdl.common.rlmmo.maintainable.world.asset.MaintainableActor;
import org.vsdl.common.rlmmo.maintainable.world.asset.MaintainableFeature;
import org.vsdl.common.rlmmo.maintainable.world.map.MaintainableField;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaintainableFieldTest {

    private MaintainableField field;

    @BeforeEach
    void setup() {
        field = MaintainableFixture.getMaintainableField();
    }

    @Test
    void testUninitializedField() {
        assertThrows(IllegalStateException.class, () -> field.getVersion());
    }

    @Test
    void testAddOneActor() {
        field.initialize();
        MaintainableActor player = MaintainableFixture.getMaintainableActor_Player();
        field.addActor(player);
        List<Actor> actors = field.getActorsAt(player.getLocation());
        assertNotNull(actors);
        assertEquals(1, actors.size());
    }

    @Test
    void testAddOneActorViaMaintenanceTransaction() {
        field.initialize();
        MaintainableActor player = MaintainableFixture.getMaintainableActor_Player();
        MaintenanceTransactionRecord record = MaintenanceTransactionRecord.initializeRecord(field.getUUID(), field.getVersion());
        MaintenanceTransaction transaction = new MaintenanceTransaction(Field.class.getCanonicalName(), "addActor", new Class[]{Actor.class}, new Object[]{player});
        record.record(transaction);
        try {
            record.applyTo(field);
        } catch (Exception e) {
            fail();
        }
        List<Actor> actors = field.getActorsAt(player.getLocation());
        assertNotNull(actors);
        assertEquals(1, actors.size());
    }

    @Test
    void testAddOneFeature() {
        field.initialize();
        MaintainableFeature expected = MaintainableFixture.getMaintainableFeature();
        field.addFeatureAt(MapLoc.at(0,0), expected);
        Feature actual = field.getFeatureAt(MapLoc.at(0,0));
        assertNotNull(actual);
        assertEquals(expected.getClass(), actual.getClass());
        assertEquals(expected.getArchetypeId(), actual.getArchetypeId());
        assertEquals(expected.getState(), actual.getState());
        assertEquals(expected.getDurability(), actual.getDurability());
    }

    @Test
    void testAddOneFeatureViaMaintenanceTransaction() {
        field.initialize();
        MaintainableFeature expected = MaintainableFixture.getMaintainableFeature();
        MaintenanceTransactionRecord record = MaintenanceTransactionRecord.initializeRecord(field.getUUID(), field.getVersion());
        MaintenanceTransaction transaction = new MaintenanceTransaction(Field.class.getCanonicalName(), "addFeatureAt", new Class[]{MapLoc.class, Feature.class}, new Object[]{MapLoc.at(0,0), expected});
        record.record(transaction);
        try {
            record.applyTo(field);
        } catch (Exception e) {
            fail();
        }
        MaintainableFeature actual = (MaintainableFeature) field.getFeatureAt(MapLoc.at(0,0));
        assertNotNull(actual);
        assertEquals(expected.getUUID(), actual.getUUID());
        assertEquals(expected.getVersion(), actual.getVersion());
    }
}
