/**
 * <p>Title : IP地址</p>
 * <p>Description :
 *
 *  net-tools : ipconfig、route、arp、netstat
 *      部分Linux已经不支持
 *  iproute2 : ip address
 *      取代net-tools
 *
 *
 *
 *  ip addr :
 *      link/ether d0:0d:c2:31:62:27
 *      inet 172.31.202.28/24 brd 172.31.202.255 scope global dynamic eth0
 *      inet6 fe80::d20d:c2ff:fe31:6227/64 scope link
 *
 *  1、ip地址，一个网卡在网络世界的通讯地址，相当于门牌号
 *  2、172.31.202.28 IP地址被分为4部分，每个部分 8bit，0-255表示
 *  3、A、B、C、D、E类IP地址
 *      A类：0 + 7位网络号 + 24位主机号
 *      B类：10 + 14位网络号 + 16位主机号
 *      C类：110 + 21位网络号 + 8位主机号
 *      D类：1110 + 28位组播号
 *      E类：11110 + 27位其他用途
 *  4、网络号：网段；
 *      主机号：每个网段允许的主机数
 *
 *
 *
 *  CIDR：无类型域间选路
 *      172.31.202.28/24：
 *          原本172网段属于B类地址，默认前16位是网络号，后16位作为主机号；
 *          /24，表示前24位是网络号，后8位是主机号
 *      172.31.202.255：广播地址
 *          将IP地址的主机号全部为 1
 *      255.255.255.0：子网掩码
 *          主机号全部为 1；其余全部为 0
 *      172.31.202.0：网络号
 *          网络号 = IP地址 AND 子网掩码
 *
 *
 *
 *  共有IP地址、私有IP地址
 *      私有IP：
 *          A类：10.0.0.0 - 10.255.255.255
 *          B类：172.16.0.0 - 172.31.255.255
 *          C类：192.168.0.0 - 192.168.255.255
 *      除此之外都是共有IP地址，有组织统一分配，需要购买使用
 *
 *
 *
 *  组播地址：D类IP地址
 *      将一些列IP地址映射编织到一个组内
 *
 *
 *  scope：
 *      网卡eth0，对应值是 global 表示对外，可以接收外部的数据包
 *      网卡lo，对应host 表示只有主机内部的进程互通
 *          lo = loopback 回环地址，127.0.0.1 = localhost
 *
 *
 *  MAC地址：
 *      link/ether d0:0d:c2:31:62:27 brd ff:ff:ff:ff:ff:ff
 *      网卡的唯一标示，身份证号
 *
 *  网卡设备状态标识：
 *      <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP qlen 1000
 *
 *      BROADCAST：表示网卡有广播地址，可以发送广播包；
 *      MULTICAST：表示网卡可以发送多播包
 *      UP：表示网卡处于启动状态；
 *      LOWER_UP：表示L1是启动的，即网线是连接的
 *
 *      mtu 1500：最大传输单元，默认 1500 byte
 *          MTU是二层(传输层)MAC层的概念
 *          以太网规定：MAC头 + 正文 <= 1500 byte
 *              正文：IP头 + TCP头 + HTTP头 + 数据
 *          如果超过1500byte则需要分片传输
 *
 *      qdisc pfifo_fast
 *          qdisc = queueing discipline 排队规则
 *          pfifo_fast：使用不同优先级的三个波段：band-0 > band-1 > band-2
 *              同一个波段优先级的数据包，采用先进先出
 *          数据包的波段(优先级)是通过，TOS = Type of Service决定的，TOS是IP头里面的一个字段
 *
 *
 * </p>
 * <p>Date : 2019-03-05 </p>
 *
 * @author : hejie
 */
package hj.action.network.c3_ipAddress;