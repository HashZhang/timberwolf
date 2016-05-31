package io.timberwolf.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * Created by 862911 on 2016/5/31.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    private long counter = 0;

    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        counter++;
    }

    public long getCounter() {
        return counter;
    }
}
