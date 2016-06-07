package io.timberwolf.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

/**
 * Created by 862911 on 2016/6/7.
 */
public class IgniteCacheGetApp {
    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start("spring-ignite/ignite-cache.xml")) {
            IgniteCache<String, Integer> igniteCache = ignite.getOrCreateCache("testCache");
            for (int i = 0; i < 100; i++) {
                System.out.println("got:" + igniteCache.get("test" + i));
            }
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
