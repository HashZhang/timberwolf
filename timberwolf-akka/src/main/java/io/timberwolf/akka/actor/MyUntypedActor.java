package io.timberwolf.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class MyUntypedActor extends UntypedActor {
    @Override
    public void onReceive(Object message) {
        System.out.println(message.toString());
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("toStringActor");
        final ActorRef toString = system.actorOf(Props.create(MyUntypedActor.class),"toString");
        for(int i=0;i<100;i++) {
            toString.tell("test"+i,toString);
        }
        System.out.println("[结束]=======================");
    }
}
