package org.vsdl.common.rlmmo.maintainable;

import org.vsdl.common.rl.world.map.MapLoc;
import org.vsdl.common.rlmmo.maintainable.world.WorldAssetClass;
import org.vsdl.common.rlmmo.maintainable.world.asset.ArchetypedAssetFactory;
import org.vsdl.common.rlmmo.maintainable.world.asset.MaintainableActor;
import org.vsdl.common.rlmmo.maintainable.world.asset.MaintainableFeature;
import org.vsdl.common.rlmmo.maintainable.world.map.MaintainableField;

public class MaintainableFixture {

    private static final ArchetypedAssetFactory assetFactory = new ArchetypedAssetFactory();

    public static MaintainableField getMaintainableField() {
        return new MaintainableField(32, 32);
    }

    public static MaintainableActor getMaintainableActor_Player() {
        MaintainableActor player = (MaintainableActor) assetFactory.manufactureAsset(WorldAssetClass.ACTOR, 0);
        player.setLocation(MapLoc.at(16, 16));
        return player;
    }

    public static MaintainableFeature getMaintainableFeature() {
        return (MaintainableFeature) assetFactory.manufactureAsset(WorldAssetClass.FEATURE, 0);
    }
}
