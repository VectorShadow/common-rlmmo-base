package org.vsdl.common.rlmmo.maintainable.events;

import org.vsdl.common.engine.utils.ManagedThread;

public class ChangePublicationScheduler extends ManagedThread {
    private final ChangePublicationHandler changePublicationHandler;
    private final long publicationInterval;


    public ChangePublicationScheduler(ChangePublicationHandler changePublicationHandler, long publicationInterval) {
        this.changePublicationHandler = changePublicationHandler;
        this.publicationInterval = publicationInterval;
    }

    @Override
    public void run() {
        doStart();
        do {
            changePublicationHandler.alterLog(null);
            try {
                Thread.sleep(publicationInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while(isRunning());
    }

}
