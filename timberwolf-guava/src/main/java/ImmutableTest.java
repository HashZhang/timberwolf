import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 862911 on 2016/5/31.
 */
public class ImmutableTest {
    public static void testImmutable(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        ImmutableSet immutableSet =ImmutableSet.copyOf(list);
        System.out.println(immutableSet.toString());
    }

    public static void main(String[] args) {
        ImmutableTest.testImmutable();
    }
}
