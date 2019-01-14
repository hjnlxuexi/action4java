package hj.action.zookeeper.watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2018/12/5 </p>
 *
 * @author : hejie
 */
public class CustomWatcher {

    public static void main(String[] args) throws KeeperException, InterruptedException {
        DefaultWatcher defaultWatcher = new DefaultWatcher();
        ZooKeeper zk = defaultWatcher.zk;
        System.out.println("1、判断节点是否存在，并watch");
        //自定义watcher
        Stat stat = zk.exists("/p2", new Watcher() {
            public void process(WatchedEvent event) {
                String path = event.getPath();
                System.out.println(String.format("path=%s, eventType=%s",path,event.getType()));
            }
        });
        if (stat == null){
            zk.create("/p2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("2、节点已创建，并触发事件");

        System.out.println("3、获取子节点，并watch");
        zk.getChildren("/p2",new Watcher() {
            public void process(WatchedEvent event) {
                String path = event.getPath();
                System.out.println(String.format("path=%s, eventType=%s",path,event.getType()));
            }
        });
        zk.create("/p2/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("4、子节点已创建，并触发事件");
        List children = zk.getChildren("/",new Watcher() {
            public void process(WatchedEvent event) {
                String path = event.getPath();
                System.out.println(String.format("path=%s, eventType=%s",path,event.getType()));
            }
        });
        System.out.println(children);

        //释放链接
        defaultWatcher.releaseConnect();
    }
}
