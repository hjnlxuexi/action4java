/**
 * <p>Title : DHCP 与 PXE</p>
 * <p>Description :
 *
 *  同一个交换机下，两台机器设置跨网段的ip，为啥不能互联？
 *      原则：只要在网络上的包，都是完整的，可以有下层没上层，绝对不可以有上层没下层。
 *
 *  分析：
 *      交换机：192.168.1.1
 *      主机A：16.158.23.6
 *      主机B：192.168.1.6
 *
 *      1、主机A --> 主机B 发不出去，因为没有MAC
 *      2、填自己的IP、自己的MAC；填目标IP、目标MAC？？
 *      3、目标MAC：先判断源IP与目标IP是否同一个网段
 *          同网段，发送ARP(地址解析协议)请求，获取主机(网卡)MAC
 *          跨网段，试图将包发送到网关
 *              如果有网关，获取网关的MAC
 *              如果没有网关，直接结束，无法发送
 *
 *  静态IP配置：
 *      CIDR、子网掩码、广播地址、网关地址
 *
 *
 *
 *  DHCP：Dynamic Host Configuration Protocol 动态主机配置协议
 *      解析流程：
 *          1、DHCP Discover广播包，新机器加入网络，发出广播包，找到DHCP Server
 *              DHCP Discover封装了UDP，
 *                  UDP封装了BOOTP，DHCP是增强版的BOOTP。
 *          2、DHCP Offer响应包，DHCP Server为这个新主机保留一个IP，并广播出去。
 *              因为新主机还没有最终确认IP地址，所以DHCP Server还只能用广播的方式告知他
 *              包内容：子网掩码、网关、IP地址、租用期限等
 *          3、DHCP Request广播包，新主机选择一个Offer，并发出IP租用请求，并广播出去
 *              包内容：本机MAC、接受的IP地址、提供租约的DHCP Server
 *              由于DHCP Server还没有最终的确认IP的租用关系，所以包装的客户端IP还是：0.0.0.0
 *          4、DHCP ACK应答包，DHCP Server接收到Request后，发出租用IP地址及其他配置信息，并广播告知。
 *
 *  IP地址收回与续租
 *      租期：
 *          客户机在租期过去50%的时候，会再次执行DHCP的流程，续约租期
 *
 *
 * ----------------------------------------------------------------------------------------------------
 *  PXE：预启动执行环境，Pre-boot Execution Environment，用于为N多主机安装操作系统
 *      1、将PXE客户端放到BIOS里
 *      2、BIOS启动调用PXE
 *      3、PXE客户端发送DHCP请求获取IP地址
 *      4、DHCP Server 除了分配IP，还配置了PXE服务端，也会一并返回给PXE客户端
 *          DHCP Server配置：
 *              IP、子网掩码、网关、租期
 *              如果需要PXE，next-server(PXE服务端IP)、filename初始启动文件
 *      5、连接PXE服务器，下载自动文件
 *      6、初始化操作系统
 * </p>
 * <p>Date : 2019-03-06 </p>
 *
 * @author : hejie
 */
package hj.action.network.c4_dhcp$pxe;