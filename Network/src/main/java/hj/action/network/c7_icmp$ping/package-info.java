/**
 * <p>Title : ICMP 与 ping</p>
 * <p>Description :
 *
 *  ICMP = Internet Control Message Protocol 互联网控制报文协议
 *      ICMP报文封装在IP包里面，即网络层(第三层)
 *
 *      查询报文类型：ping
 *          ping就是查询报文，是一种主动请求，并且获得主动应答的ICMP协议。所以ping包服务ICMP协议格式，并添加自己的格式
 *          ping请求：ICMP ECHO REQUEST
 *          ping应答：ICMP ECHO REPLY
 *
 *      差错报文类型：traceroute
 *          终点不可达：3
 *              网络不可达：0
 *              主机不可达：1
 *              协议不可达：2
 *              端口不可达：3
 *              需要分片但设置为部分片：4
 *          源抑制：4
 *          超时：11
 *          重定向：5
 *
 * -----------------------------------------------------------------------------------------------------------
 *      ping过程
 *          1、构建ICMP包
 *              类型字段：8
 *              顺序号：++1，连续ping时数据报数量
 *              报文数据中加入发送时间，为了计算往返时间(RTT)
 *          2、加入源IP、目标IP，构建IP数据包
 *          3、添加MAC头
 *              通过本地ARP映射表获取MAC地址
 *              如果没有MAC地址，则通过ARP获取
 *          4、到达目标主机
 *          5、数据包层层解析
 *          6、再层层包装回应数据
 *      通过 tcpdump -i eth0 icmp，查看包有没有到达某个点，回复的包到达了那个点，可以更加容易推断出错的位置
 *
 *
 *      traceroute过程：
 *          1、故意设置特殊的TTL = Time to Live 存活时间，来追踪去往目的是沿途经过的路由器
 *              Traceroute的参数指向某个目的的IP，它会发送一个 UDP 的数据包（一般选择大于30000的端口号）。
 *              将 TTL 设置为 1，也就是一旦遇到一个或由其或者关卡，就表示"牺牲"。
 *              所以，通过反复的牺牲尝试，以及调整TTL的值，直到到达目标主机。
 *              整个过程结束后，TraceRoute就拿到了一路上所有路由器的IP地址(但有的路由器不会响应ICMP)
 *          2、故意设置不分片，从而确定路径的MTU = Must Transmission Until最大传输单元
 *
 * </p>
 * <p>Date : 2019-03-08 </p>
 *
 * @author : hejie
 */
package hj.action.network.c7_icmp$ping;