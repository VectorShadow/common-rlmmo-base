package org.vsdl.common.rlmmo.maintainable.world.asset;

import org.vsdl.common.rlmmo.maintainable.template.MaintainableAsset;
import org.vsdl.common.rlmmo.maintainable.world.WorldAssetClass;

public class ArchetypedAssetFactory {
    public MaintainableAsset manufactureAsset(WorldAssetClass assetClass, int archetypeId) {
        if (archetypeId < 0 || archetypeId > Short.MAX_VALUE) {
            throw new IllegalArgumentException("ArchetypeID out of range(>=0, <=32767, was: " + archetypeId + ").");
        }
        short typedId = (short)archetypeId;
        MaintainableAsset manufacturedAsset;
        switch (assetClass) {
            case ACTOR -> manufacturedAsset = new MaintainableActor(typedId);
            case FIELD -> throw new IllegalArgumentException("MaintainableField is not an Archetyped Asset.");
            default -> throw new IllegalArgumentException("Unsupported class: " + assetClass);
        }
        manufacturedAsset.initialize();
        return manufacturedAsset;
    }
}
