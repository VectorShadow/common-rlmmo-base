package org.vsdl.common.rlmmo.maintainable.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vsdl.common.rl.world.asset.Actor;
import org.vsdl.common.rlmmo.maintainable.MaintainableFixture;
import org.vsdl.common.rlmmo.maintainable.world.asset.MaintainableActor;
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
}
