package org.vsdl.common.rlmmo.maintainable.template;

import org.vsdl.common.mmo.consistency.Maintainable;

import java.util.UUID;

public interface MaintainableAsset extends Maintainable {

    void initialize();

    boolean isInitialized();

    static UUID generateUUID() {
        return UUID.randomUUID();
    }
}
