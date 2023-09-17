package org.vsdl.common.rlmmo.maintainable.template;

import java.util.UUID;

/**
 * Java does not support multiple inheritance.
 * However, all classes implementing MaintainableAsset should use this class as a template.
 */
public class MaintainableAssetTemplate implements MaintainableAsset {

    private Boolean isInitialized = false;
    private UUID uuid = null;
    private long version = -1L;

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
