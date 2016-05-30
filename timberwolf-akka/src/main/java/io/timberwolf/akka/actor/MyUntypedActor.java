package io.timberwolf.akka.actor;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class MyUntypedActor extends UntypedActor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyUntypedActor.class);

    @Override
    public void onReceive(Object message) throws Exception {
        LOGGER.info("Received message: " + message);
        getSender().tell(message,getSelf());
    }
}
