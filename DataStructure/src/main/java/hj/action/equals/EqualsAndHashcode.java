package hj.action.equals;

/**
 * <p>Title : equals与hashcode的基情组合</p>
 * <p>Description :
 *  1、在Java中hashCode的实现总是伴随着equals，他们是紧密配合的，你要是自己设计了其中一个，就必须要设计另外一个。
 *
 *  2、两个对象，equals相等，hashcode则一定相等。
 *
 *  3、在Set集合，或者基于Set的集合，比如HashMap，HashTable，HashSet等，数据元素是无序不重复的，而这个不重复的的特性是通过hashcode和equals来保障的
 *
 * </p>
 * <p>Date : 2018/12/25 </p>
 *
 * @author : hejie
 */
public class EqualsAndHashcode {
}
