package org.vsdl.common.rlmmo.maintainable.world.asset;

import org.vsdl.common.rl.world.asset.Feature;
import org.vsdl.common.rlmmo.maintainable.template.MaintainableAsset;

import java.util.UUID;

public class MaintainableFeature extends Feature implements MaintainableAsset {

    private Boolean isInitialized = false;
    private UUID uuid = null;
    private long version = -1L;

    protected MaintainableFeature(short archetypeId) {
        super(archetypeId);
    }

    @Override
    public UUID getUUID() {
        if (!isInitialized()) throw new IllegalStateException("MaintainableAsset has not been initialized!");
        return uuid;
    }

    @Override
    public long getVersion() {
        if (!isInitialized()) throw new IllegalStateException("MaintainableAsset has not been initialized!");
        return version;
    }

    @Override
    public void incrementVersion() {
        if (!isInitialized()) throw new IllegalStateException("MaintainableAsset has not been initialized!");
        ++version;
    }

    @Override
    public void initialize() {
        version = 0L;
        uuid = MaintainableAsset.generateUUID();
        isInitialized = true;
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }
}
