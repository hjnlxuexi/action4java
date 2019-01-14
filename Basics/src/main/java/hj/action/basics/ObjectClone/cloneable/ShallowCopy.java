package hj.action.basics.ObjectClone.cloneable;

/**
 * <p>Title : 浅拷贝</p>
 * <p>Description :
 * 1、通过实现Cloneable接口；
 * 2、直接利用Object.clone();
 *
 * 浅拷贝：
     1、 基本类型：如果变量是基本很类型，则拷贝其值，比如int、float等。
     2、 对象：如果变量是一个实例对象，则拷贝其地址引用，也就是说此时新对象与原来对象是公用该实例变量（关键问题）。
     3、 String字符串：若变量为String字符串，则拷贝其地址引用。但是在修改时，它会从字符串池中重新生成一个新的字符串，原有字符串对象保持不变。
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class ShallowCopy {

    static class Person implements Cloneable {
        private String name;
        private int age;
        private Song song;

        Person(String name , int age){
            this.name = name;
            this.age = age;
        }

        @Override
        protected Person clone() throws CloneNotSupportedException {
            return (Person)super.clone();//采用 Object.clone()
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Song getSong() {
            return song;
        }

        public void setSong(Song song) {
            this.song = song;
        }
    }

    static class Song {
        Song(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Song song = new Song("一生所爱");
        Person z3 = new Person("张三", 3);
        z3.setSong(song);

        Person l4 = z3.clone();
        l4.setName("李四");
        l4.setAge(4);

        Person w5 = z3.clone();
        w5.setName("王五");
        w5.setAge(5);
        //只拷贝了对象的地址引用
        w5.getSong().setName("张三的歌");


        System.out.println(z3.getName()+"，"+z3.getAge()+"岁，唱的歌："+z3.getSong().getName());
        System.out.println(l4.getName()+"，"+l4.getAge()+"岁，唱的歌："+l4.getSong().getName());
        System.out.println(w5.getName()+"，"+w5.getAge()+"岁，唱的歌："+w5.getSong().getName());
    }
}
