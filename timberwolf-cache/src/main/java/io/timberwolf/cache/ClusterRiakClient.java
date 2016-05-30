package io.timberwolf.cache;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.buckets.ListBuckets;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.ListKeys;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakFuture;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.timberwolf.common.domain.User;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * The client class of Riak
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see @https://github.com/basho/riak-java-client/wiki/RiakClient-%26-Cluster-Node-Builders-%28v2.0%29
 */
public class ClusterRiakClient implements AutoCloseable {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static int DEFAULTMAXCONNECTIONS = 50;
    private final static int DEFAULTMINCONNECTIONS = 10;
    private final static int RETRIES = 5;

    private int maxConnections = DEFAULTMAXCONNECTIONS;
    private int minConnections = DEFAULTMINCONNECTIONS;
    private int retries = DEFAULTMINCONNECTIONS;

    private RiakClient riakClient;

    /**
     * 构建Riak集群的客户端
     *
     * @param hosts (hosts格式：127.0.0.1:10017,127.0.0.1:10027,127.0.0.1:10037)
     * @throws UnknownHostException
     */
    public ClusterRiakClient(String hosts) throws UnknownHostException {
        String[] addresses = hosts.split(",");
        //使用RiakNode Builder用于构建每个RiakNode
        RiakNode.Builder riakNodeBuilder = new RiakNode.Builder()
                .withMinConnections(minConnections)
                .withMaxConnections(maxConnections);
        //构建每个RiakNode并保存在list中
        LinkedList<RiakNode> nodes = new LinkedList<RiakNode>();
        for (int i = 0; i < addresses.length; i++) {
            int j = addresses[i].indexOf(":");
            nodes.add(riakNodeBuilder.withRemoteAddress(addresses[i].substring(0, j))
                    .withRemotePort(Integer.parseInt(addresses[i].substring(j + 1))).build());
        }
        //构建Riak集群并启动，注意，必须调用start()
        RiakCluster riakCliuster = RiakCluster.builder(nodes).withExecutionAttempts(retries).build();
        riakCliuster.start();
        riakClient = new RiakClient(riakCliuster);
    }

    /**
     * 插入字符数据
     *
     * @param bucket 桶
     * @param key    键
     * @param value  值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void set(String bucket, String key, String value) throws ExecutionException, InterruptedException {
        Location location = new Location(new Namespace(bucket), key);
        StoreValue sv = new StoreValue.Builder(value).withLocation(location).build();
        StoreValue.Response svResponse = this.riakClient.execute(sv);
    }


    /**
     * 插入POJO对象
     *
     * @param bucket 桶
     * @param key    键
     * @param value  值
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JsonProcessingException
     */
    public void set(String bucket, String key, Object value) throws ExecutionException, InterruptedException, JsonProcessingException {
        Location location = new Location(new Namespace(bucket), key);
        RiakObject riakObject = new RiakObject();
        riakObject.setValue(BinaryValue.create(OBJECT_MAPPER.writeValueAsBytes(value)));
        StoreValue sv = new StoreValue.Builder(riakObject).withLocation(location).build();
        this.riakClient.execute(sv);
    }

    /**
     * 异步插入POJO对象
     *
     * @param bucket 桶
     * @param key    键
     * @param value  值
     * @return RiakFuture<StoreValue.Response, Location>
     * *                                      +---------------------------+
     * | Completed successfully    |
     * +---------------------------+
     * +---->      isDone() = <b>true</b>      |
     * +--------------------------+    |    |   isSuccess() = <b>true</b>      |
     * |        Uncompleted       |    |    +===========================+
     * +--------------------------+    |    | Completed with failure    |
     * |      isDone() = <b>false</b>    |    |    +---------------------------+
     * |   isSuccess() = false    |----+---->      isDone() = <b>true</b>      |
     * | isCancelled() = false    |    |    |   isSuccess() = <b>false</b>     |
     * |       cause() = null     |    |    |       cause() = <b>non-null</b>  |
     * +--------------------------+    |    +===========================+
     * |    | Completed by cancellation |
     * |    +---------------------------+
     * +---->      isDone() = <b>true</b>      |
     * | isCancelled() = <b>true</b>      |
     * +---------------------------+
     * @throws JsonProcessingException
     */
    public RiakFuture<StoreValue.Response, Location> asyncSet(String bucket, String key, Object value) throws JsonProcessingException {
        Location location = new Location(new Namespace(bucket), key);
        RiakObject riakObject = new RiakObject();
        riakObject.setValue(BinaryValue.create(OBJECT_MAPPER.writeValueAsBytes(value)));
        StoreValue sv = new StoreValue.Builder(riakObject).withLocation(location).build();
        return this.riakClient.executeAsync(sv);
    }

    /**
     * 取得POJO对象
     *
     * @param bucket 桶
     * @param key    键
     * @return FetchValue.Response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public RiakObject get(String bucket, String key) throws ExecutionException, InterruptedException {
        Location location = new Location(new Namespace(bucket), key);
        FetchValue fv = new FetchValue.Builder(location).build();
        return this.riakClient.execute(fv).getValue(RiakObject.class);
    }

    /**
     * 异步取得POJO对象
     *
     * @param bucket 桶
     * @param key    键
     * @return RiakFuture<FetchValue.Response, Location>
     */
    public RiakFuture<FetchValue.Response, Location> asyncGet(String bucket, String key) {
        Location location = new Location(new Namespace(bucket), key);
        FetchValue fv = new FetchValue.Builder(location).build();
        return this.riakClient.executeAsync(fv);
    }

    /**
     * 删除一个键值对
     *
     * @param bucket 桶
     * @param key    键
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void remove(String bucket, String key) throws ExecutionException, InterruptedException {
        Location location = new Location(new Namespace(bucket), key);
        DeleteValue dv = new DeleteValue.Builder(location).build();
        this.riakClient.execute(dv);
    }

    /**
     * 异步删除一个键值对
     *
     * @param bucket 桶
     * @param key    键
     * @return RiakFuture<Void, Location>
     */
    public RiakFuture<Void, Location> asyncRemove(String bucket, String key) {
        Location location = new Location(new Namespace(bucket), key);
        DeleteValue dv = new DeleteValue.Builder(location).build();
        return this.riakClient.executeAsync(dv);
    }

    /**
     * 取得一个bucket下所有键
     *
     * @param bucket 桶
     * @return List<String>
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> getKeys(String bucket) throws ExecutionException, InterruptedException {
        Namespace ns = new Namespace(bucket);
        ListKeys lk = new ListKeys.Builder(ns).build();
        ListKeys.Response response = this.riakClient.execute(lk);
        List<String> keys = new LinkedList<>();
        for (Location l : response)
        {
            keys.add(l.getKeyAsString());
        }
        return keys;
    }


    public static void main(String[] args) throws Exception {
        final ClusterRiakClient clusterRiakClient = new ClusterRiakClient("10.202.44.206:10017,10.202.44.206:10027,10.202.44.206:10037");
//        clusterRiakClient.remove("users", "user1");
//        RiakObject riakObject = clusterRiakClient.get("users", "user1");
//        if (riakObject!=null) {
//            User getUser1 = OBJECT_MAPPER.readValue(riakObject.getValue().getValue(), User.class);
//            System.out.println(getUser1.getUsername() + "|" + getUser1.getPassword());
//        } else{
//            System.out.println("Not Found");
//        }
        //        final CountDownLatch countDownLatch = new CountDownLatch(1);
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                User user1 = new User();
//                user1.setUsername("zhxhash").setPassword("123456");
//                RiakFuture<StoreValue.Response, Location> riakFuture = null;
//                while (true) {
//                    try {
//                        riakFuture = clusterRiakClient.asyncSet("users", "user1", user1);
//                        riakFuture.await(100L, TimeUnit.MILLISECONDS);
//                    } catch (JsonProcessingException | InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if (riakFuture.isDone() && riakFuture.isSuccess()) {
//                        countDownLatch.countDown();
//                        break;
//                    }
//                }
//            }
//        };
//        thread.start();
//        countDownLatch.await();
//        RiakFuture<FetchValue.Response, Location> riakFuture = null;
//        while (true) {
//            try {
//                riakFuture = clusterRiakClient.asyncGet("users", "user1");
//                riakFuture.await(100L, TimeUnit.MILLISECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (riakFuture.isDone() && riakFuture.isSuccess()) {
//                RiakObject riakObject = riakFuture.get().getValue(RiakObject.class);
//                User getUser1 = OBJECT_MAPPER.readValue(riakObject.getValue().getValue(), User.class);
//                System.out.println(getUser1.getUsername() + "|" + getUser1.getPassword());
//                break;
//            }
//        }
        List<String> keys = clusterRiakClient.getKeys("users");
        System.out.println(keys.toString());
        clusterRiakClient.close();

    }

    public void close() throws Exception {
        riakClient.shutdown();
    }
}
