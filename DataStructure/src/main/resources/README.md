```txt
原文：https://www.cnblogs.com/xdecode/p/9321848.html
```

## 数据结构

### LinkedList

经典的双链表结构, 非连续的内存空间。适用于乱序插入, 删除. 指定序列操作则性能不如ArrayList, 这也是其数据结构决定的.



**指定位置添加/删除：add(index, E) / remove(index)**

这边有个小的优化, 他会先判断index是靠近队头还是队尾, 来确定从哪个方向遍历链入.

```java
if (index < (size >> 1)) {
    Node<E> x = first;
    for (int i = 0; i < index; i++)
        x = x.next;
    return x;
} else {
    Node<E> x = last;
    for (int i = size - 1; i > index; i--)
        x = x.prev;
    return x;
}
```

**指定位置获取：get(index)**

类似 add(index,E) 也是会先判断index, 不过性能依然不好, 这也是为什么不推荐用for(int i = 0; i < lengh; i++)的方式遍历linkedlist, 而是使用iterator的方式遍历.

**指定元素删除：remove(E)**

需要从头开始遍历，知道找到匹配的元素

```java
public boolean remove(Object o) {
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null) {
                unlink(x);
                return true;
            }
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) {
                unlink(x);
                return true;
            }
        }
    }
    return false;
}
```



### ArrayList

底层就是一个数组，连续的内存空间, 因此按序查找快,。乱序插入, 删除因为涉及到后面元素移位所以性能慢.



**扩容**

一般默认容量是10, 扩容后, 会length*1.5.

**remove(E)**

循环遍历数组, 判断E是否equals当前元素, 找到并删除元素后，还要搬移其余的元素，所以删除性能不如LinkedList.



### Stack

经典的数据结构, 底层也是数组, 继承自Vector, 先进后出FILO, 默认new Stack()容量为10, 超出自动扩容.



**后缀表达式**

Stack的一个典型应用就是计算表达式如 9 + (3 - 1) * 3 + 10 / 2, 计算机将中缀表达式转为后缀表达式, 再对后缀表达式进行计算.

**中缀转后缀**

- 数字直接输出
- 栈为空时，遇到运算符，直接入栈
- 遇到左括号, 将其入栈
- 遇到右括号, 执行出栈操作，并将出栈的元素输出，直到弹出栈的是左括号，左括号不输出。
- 遇到运算符(加减乘除)：弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈
- 最终将栈中的元素依次出栈，输出。

**计算后缀表达**

- 遇到数字时，将数字压入堆栈
- 遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算, 并将结果入栈
- 重复上述过程直到表达式最右端
- 运算得出的值即为表达式的结果



### ArrayBlockingQueue

生产消费者中常用的阻塞有界队列, FIFO.

**take()**

当元素被取出后, 并没有对数组后面的元素位移, 而是更新takeIndex来指向下一个元素.

takeIndex是一个环形的增长, 当移动到队列尾部时, 会指向0, 再次循环.



### HashMap

最常用的哈希表, 面试的童鞋必备知识了, 内部通过数组 + 单链表的方式实现. jdk8中引入了红黑树对长度 > 8的链表进行优化.



**resize 动态扩容**

HashMap 默认容量为16，当map中元素超出设定的阈值后, 会进行resize (length * 2)操作, 扩容过程中对元素一通操作, 并放置到新的位置.

具体操作如下:

- 在jdk7中对所有元素直接rehash, 并放到新的位置.
- 在jdk8中判断元素原hash值新增的bit位是0还是1, 0则索引不变, 1则索引变成"原索引 + oldTable.length".



### LinkedHashMap

继承自HashMap, 底层额外维护了一个双向链表来维持数据有序. 可以通过设置accessOrder来实现FIFO(插入有序)或者LRU(访问有序)缓存.



**get(K)**

accessOrder为false的时候, 直接返回元素就行了, 不需要调整位置. 

accessOrder为true的时候, 需要将最近访问的元素, 放置到队尾.