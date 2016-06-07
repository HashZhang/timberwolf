package io.timberwolf.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteCallable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by 862911 on 2016/6/7.
 */
public class IgniteComputeSendApp {
    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start("spring-ignite/ignite-cache.xml")) {
            Collection<IgniteCallable<Integer>> calls = new ArrayList<>();

            // Iterate through all the words in the sentence and create Callable jobs.
            for (final String word : "Count characters using callable".split(" ")) {
                calls.add(new IgniteCallable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return word.length();
                    }
                });
            }

            // Execute collection of Callables on the grid.
            Collection<Integer> res = ignite.compute().call(calls);

            int sum = 0;

            // Add up individual word lengths received from remote nodes.
            for (int len : res)
                sum += len;

            System.out.println(">>> Total number of characters in the phrase is '" + sum + "'.");
        }
    }
}
