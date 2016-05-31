package io.timberwolf.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Hash Zhang on 2016/5/31.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
