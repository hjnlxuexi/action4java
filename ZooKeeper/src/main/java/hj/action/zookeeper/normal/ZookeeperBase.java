package hj.action.zookeeper.normal;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import sun.font.TrueTypeFont;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2018/12/5 </p>
 *
 * @author : hejie
 */
public class ZookeeperBase {
    private static final int SESSION_TIMEOUT = 5000;//ms
    private static final String CONNECTSTRING = "172.16.193.200:2181";

    //由于zk连接是异步的，需要保障连接成功之后，才能执行后续操作
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        //1、连接 zk
        ZooKeeper zk = new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //获取事件状态
                Event.KeeperState keeperState = watchedEvent.getState();
                //获取事件类型
                Event.EventType eventType = watchedEvent.getType();
                //发生变化的节点路径
                String path = watchedEvent.getPath();
                //如果是建立连接
                if (Event.KeeperState.SyncConnected == keeperState) {
                    if (Event.EventType.None == eventType) {
                        //如果连接成功，则发送信号量，让后续程序执行
                        COUNT_DOWN_LATCH.countDown();
                        System.out.println("zk 连接成功！");
                    }

                    if (Event.EventType.None != eventType) {
                        System.out.println("变化的节点："+ path);
                        System.out.println("事件类型："+ eventType);
                    }
                }
            }
        });
        //2、阻塞 等待连接完成
        COUNT_DOWN_LATCH.await();


        //3、业务操作
        System.out.println("0、查看子节点");
        List children1 = zk.getChildren("/",true);
        System.out.println("1、判断节点是否存在");
        Stat stat = zk.exists("/testRoot", true);
        System.out.println("2、删除节点");
        if (stat != null)
            zk.delete("/testRoot",-1);

        System.out.println("3、创建节点");
        zk.create("/testRoot", "testRoot".getBytes() , ZooDefs.Ids.OPEN_ACL_UNSAFE , CreateMode.PERSISTENT);


        System.out.println("4、查看子节点");
        List children = zk.getChildren("/",true);
        System.out.println(children);




        //关闭zk连接
        zk.close();
    }
}
