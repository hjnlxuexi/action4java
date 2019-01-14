package hj.action.rmq.quickstart;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * <p>Title : 生产者-同步</p>
 * <p>Description : 产生消息数据，保障重要数据一致性，消耗时间性能</p>
 * <p>Date : 2018/12/2 </p>
 *
 * @author : hejie
 */
public class ProducerSync {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        //需要一个producer group名字作为构造方法的参数，这里为producer1
        DefaultMQProducer producer = new DefaultMQProducer("PRODUCER_1");

        //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        producer.setNamesrvAddr("172.16.193.200:9876");
        producer.setVipChannelEnabled(false);

        //为避免程序启动的时候报错，添加此代码，可以让rocketMq自动创建 topic key
        //producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        producer.start();


        for(int i=0;i<10;i++){
            try {
                Message message = new Message("TopicTest", "Tag1",
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                SendResult sendResult = producer.send(message);

                System.out.println("发送的消息ID:" + sendResult.getMsgId() +"--- 发送消息的状态：" + sendResult.getSendStatus());
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();
    }
}
