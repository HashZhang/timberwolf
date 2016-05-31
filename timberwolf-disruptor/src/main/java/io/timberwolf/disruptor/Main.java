package io.timberwolf.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/**
 * Created by Hash Zhang on 2016/5/31.
 */
public class Main {
    static class Translator implements EventTranslatorOneArg<LongEvent, Long> {

        public void translateTo(LongEvent longEvent, long l, Long aLong) {
            longEvent.setValue(aLong);
        }
    }

    private static Translator TRANSLATOR = new Translator();
    private static Runnable runnable = new Runnable() {
        private final Random random = new Random();
        public void run() {
            RingBuffer<LongEvent> ringBuffer = longEventDisruptor.getRingBuffer();
            for (int i = 0; i < 100000000 ; i++) {
                long r = random.nextLong();
                ringBuffer.publishEvent(TRANSLATOR,r);
            }
        }
    };
    private static Disruptor<LongEvent> longEventDisruptor;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        WaitStrategy waitStrategy = new YieldingWaitStrategy();
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        int ringBufferSize = 1024*16;
        longEventDisruptor = new Disruptor<LongEvent>(eventFactory,ringBufferSize,executorService, ProducerType.SINGLE,waitStrategy);
        LongEventHandler eventHandler = new LongEventHandler();
        longEventDisruptor.handleEventsWith(eventHandler);
        longEventDisruptor.start();
        Thread thread = new Thread(runnable);
        long start = System.currentTimeMillis();
        thread.start();
        thread.join();
        long end = System.currentTimeMillis();
        long elapsed = end-start;
        System.out.println("Time elapsed: " + elapsed);
        System.out.println(eventHandler.getCounter());
    }

}
