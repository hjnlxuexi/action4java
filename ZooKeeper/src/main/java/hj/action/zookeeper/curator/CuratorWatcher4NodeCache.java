package hj.action.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title : 监听当前节点</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class CuratorWatcher4NodeCache {
    private static final int SESSION_TIMEOUT = 5000;//ms
    private static final String CONNECTSTRING = "172.16.193.200:2181";

    public static void main(String[] args) throws Exception {
        //1、重试策略：初始时间为1s，重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        //2、通过工厂连接
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECTSTRING)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .build();
        //3、开启连接
        cf.start();

        //4、创建一个cache缓存，用于监听当前节点
        final NodeCache cache = new NodeCache(cf , "/x", false);
        cache.start(true);

        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.println("path:"+cache.getCurrentData().getPath());
                System.out.println("data:"+new String(cache.getCurrentData().getData()));
                System.out.println("PATH:"+cache.getPath());
                System.out.println("----------------------");
            }
        });
        Thread.sleep(1000);
        System.out.println("创建xx");
        cf.create().creatingParentsIfNeeded().forPath("/x/xx");
        Thread.sleep(1000);
        System.out.println("创建ss");
        cf.create().creatingParentsIfNeeded().forPath("/x/ss");
        Thread.sleep(1000);
        System.out.println("创建ss/sss");
        cf.create().creatingParentsIfNeeded().forPath("/x/ss/sss");
        Thread.sleep(1000);
        System.out.println("设置x");
        cf.setData().forPath("/x","123".getBytes());
        Thread.sleep(1000);
        System.out.println("删除ss");
        cf.delete().guaranteed().deletingChildrenIfNeeded().forPath("/x/ss");
        Thread.sleep(1000);
        //Thread.sleep(5000);
    }
}
