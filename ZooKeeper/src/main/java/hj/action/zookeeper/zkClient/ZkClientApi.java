package hj.action.zookeeper.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2018/12/5 </p>
 *
 * @author : hejie
 */
public class ZkClientApi {

    public static void main(String[] args) {
        ZkClient zkc = new ZkClient("172.16.193.200",3000);

        //订阅Watch监听
        zkc.subscribeChildChanges("/x", new IZkChildListener() {
            /**
             * 监听子节点变化
             * @param parentPath
             * @param currentChilds
             * @throws Exception
             */
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("父节点："+parentPath);
                System.out.println("当前子节点树："+currentChilds);
            }
        });
        //订阅节点数据监听
        zkc.subscribeDataChanges("/x", new IZkDataListener() {
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            public void handleDataDeleted(String dataPath) throws Exception {

            }
        });

        //创建临时节点
        System.out.println("创建历史目录");
        zkc.createEphemeral("/ss");
        //创建持久化节点
        System.out.println("创建子目录");
        zkc.createPersistent("/x/xx/xxx/xxxx/xxxxx",true);
    }
}
