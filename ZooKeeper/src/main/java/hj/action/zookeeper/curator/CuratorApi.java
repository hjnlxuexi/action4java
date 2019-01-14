package hj.action.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class CuratorApi {
    private static final int SESSION_TIMEOUT = 5000;//ms
    private static final String CONNECTSTRING = "172.16.193.200:2181";

    public static void main(String[] args) throws Exception {
        //1、重试策略：初始时间为1s，重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

        //2、通过工厂连接
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECTSTRING)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .build();
        //3、开启连接
        cf.start();

        //4、业务

        //添加
        System.out.println("1、创建节点");
        cf.create().creatingParentsIfNeeded()
                //不设置withMode时，默认为持久化节点
                .withMode(CreateMode.PERSISTENT)
                .forPath("/x/xx");
        //删除
        System.out.println("2、删除子节点");
        cf.delete().guaranteed().deletingChildrenIfNeeded().forPath("/x/xx");

        //判断
        cf.checkExists().forPath("/x/xx");

        //获取子节点
        cf.getChildren().forPath("/x");

        //监听回调
        System.out.println("3、创建二级节点，并添加监听");
        //final CountDownLatch cdl = new CountDownLatch(1);
        //线程池，处理回调，节省线程切换开销
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //异步处理，可以在增删改查任何操作中绑定
        cf.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println(event.getType());
                        System.out.println(event.getPath());
                        client.create().creatingParentsIfNeeded()
                                //不设置withMode时，默认为持久化节点
                                .withMode(CreateMode.PERSISTENT)
                                .forPath("/x/ppppp");
                        //cdl.countDown();
                    }
                },pool).forPath("/x/xx");
        //等待异步执行
        //cdl.await();
        //Thread.sleep(1000);

        //System.out.println("4、创建三级节点");
        //cf.create().creatingParentsIfNeeded()
        //        //不设置withMode时，默认为持久化节点
        //        .withMode(CreateMode.PERSISTENT)
        //        .forPath("/x/xx/xxx");
        //
        //System.out.println("5、创建二级节点");
        //cf.create().creatingParentsIfNeeded()
        //        //不设置withMode时，默认为持久化节点
        //        .withMode(CreateMode.PERSISTENT)
        //        .forPath("/x/ss");
        //System.out.println("6、创建二级节点");
        //cf.create().creatingParentsIfNeeded()
        //        //不设置withMode时，默认为持久化节点
        //        .withMode(CreateMode.PERSISTENT)
        //        .forPath("/x/tt");

        //关闭线程池
        pool.shutdown();
    }
}
