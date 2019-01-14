package hj.action.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>Title : 监听子节点</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class CuratorWatcher4PathChildrenCache {
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

        //4、建立一个 PathChinaCache缓存，并缓存数据
        PathChildrenCache cache = new PathChildrenCache(cf , "/x",true);
        //5、在初始化的时候进行缓存监听
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        cache.getListenable().addListener(new PathChildrenCacheListener() {
            /**
             * 监控子节点变动
             * @param client zk客户端
             * @param event 时间
             * @throws Exception
             */
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("TYPE:" + event.getType()+ ", PATH: "+ event.getData().getPath()+", DATA: "+new String(event.getData().getData()));
                System.out.println("-------------------------------");
            }
        });

        Thread.sleep(1000);
        System.out.println("创建节点");
        cf.create().creatingParentsIfNeeded().forPath("/x/xx");
        Thread.sleep(1000);
        System.out.println("创建节点");
        cf.create().creatingParentsIfNeeded().forPath("/x/ss");
        Thread.sleep(1000);
        System.out.println("修改节点");
        cf.setData().forPath("/x/xx","dataX".getBytes());
        Thread.sleep(1000);
        System.out.println("删除节点");
        cf.delete().forPath("/x/xx");
        Thread.sleep(1000);
    }
}
