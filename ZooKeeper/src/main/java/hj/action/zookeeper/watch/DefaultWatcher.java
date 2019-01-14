package hj.action.zookeeper.watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Title : Watcher用法</p>
 * <p>Description :
 * Watcher绑定于查询操作，如：exists、getChildren、getData等，将目标节点与Watch绑定到一起；
 * 当后面最近的一次操作触发目标节点操作时，执行watch，随之watcher将被移除；
 * watcher的绑定于触发是成对的关系，且触发则失效。
 * </p>
 * <p>Date : 2018/12/5 </p>
 *
 * @author : hejie
 */
public class DefaultWatcher {

    private static final int SESSION_TIMEOUT = 5000;//ms
    private static final String CONNECTSTRING = "172.16.193.200:2181";

    //由于zk连接是异步的，需要保障连接成功之后，才能执行后续操作
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    ZooKeeper zk = null;

    DefaultWatcher(){
        reconnect();
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        DefaultWatcher defaultWatcher = new DefaultWatcher();
        ZooKeeper zk = defaultWatcher.zk;
        System.out.println("1、判断节点是否存在，并watch");
        Stat stat = zk.exists("/p3",true);
        zk.create("/p100","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        if (stat == null){
            zk.create("/p3","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("2、节点已创建，并触发事件");

        System.out.println("3、获取子节点，并watch");
        zk.getChildren("/p3",true);
        zk.create("/p3/c3","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("4、子节点已创建，并触发事件");
        List children = zk.getChildren("/",true);
        System.out.println(children);

        //释放链接
        defaultWatcher.releaseConnect();
    }

    public void releaseConnect() {
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void reconnect() {
        if (zk != null)
            return;
        try {
            zk = new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, new Watcher() {
                public void process(WatchedEvent event) {
                    //获取事件状态
                    Event.KeeperState keeperState = event.getState();
                    //获取事件类型
                    Event.EventType eventType = event.getType();
                    //如果是建立连接
                    if (Event.KeeperState.SyncConnected == keeperState) {
                        if (Event.EventType.None == eventType) {
                            //如果连接成功，则发送信号量，让后续程序执行
                            COUNT_DOWN_LATCH.countDown();
                            System.out.println("zk 连接成功！");
                        }
                        String path = event.getPath();
                        System.out.println("---------------------------------------------");
                        System.out.println(String.format("被监听的节点路径：%s，触发事件：%s",path,eventType));
                        System.out.println("---------------------------------------------");
                    }
                }
            });
            COUNT_DOWN_LATCH.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
