package org.vsdl.common.rlmmo.maintainable.world;

import org.vsdl.common.rlmmo.maintainable.world.asset.MaintainableActor;
import org.vsdl.common.rlmmo.maintainable.world.map.MaintainableField;

public enum WorldAssetClass {
    FIELD(MaintainableField.class),
    ACTOR(MaintainableActor.class);

    private final Class<?> assetClass;

    WorldAssetClass(Class<?> assetClass) {
        this.assetClass = assetClass;
    }

    public Class<?> getAssetClass() {
        return assetClass;
    }
}
