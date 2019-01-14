package hj.action.zookeeper.distributedlock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>Title : 用zk实现分布式锁</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class DistributedLockByZK {

    private static final int SESSION_TIMEOUT = 5000;//ms
    private static final String CONNECTSTRING = "172.16.193.200:2181";
    private static CuratorFramework createCurator(){
        //1、重试策略：初始时间为1s，重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        //2、通过工厂连接
        return CuratorFrameworkFactory.builder()
                .connectString(CONNECTSTRING)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .build();
    }

    public static void main(String[] args) {
        final CuratorFramework cf = createCurator();
        for (int i=0; i<10; i++){
            new Thread(new Runnable() {
                public void run() {
                    cf.start();
                    final InterProcessMutex lock = new InterProcessMutex(cf , "/x");
                    final InterProcessSemaphoreMutex lock1 = new InterProcessSemaphoreMutex(cf , "/x");

                    try {
                        lock.acquire();
                        System.out.println("执行业务内容，running...");
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            lock.release();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            },"thread-"+i).start();
        }
    }
}
