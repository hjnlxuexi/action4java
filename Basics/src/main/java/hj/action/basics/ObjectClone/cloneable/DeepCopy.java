package hj.action.basics.ObjectClone.cloneable;

/**
 * <p>Title : 浅拷贝</p>
 * <p>Description :
 * 1、通过实现Cloneable接口；
 * 2、直接利用Object.clone();
 *
 * 深拷贝：
    自定义clone方法，并重新new成员对象；
    增加代码编写与维护难度。
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class DeepCopy {

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
            Person person = (Person)super.clone();//采用 Object.clone()
            //手动处理，成员对象
            person.setSong(new Song(person.getSong().getName()));
            return person;
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
        //内部手动处理了成员对象
        w5.getSong().setName("张三的歌");


        System.out.println(z3.getName()+"，"+z3.getAge()+"岁，唱的歌："+z3.getSong().getName());
        System.out.println(l4.getName()+"，"+l4.getAge()+"岁，唱的歌："+l4.getSong().getName());
        System.out.println(w5.getName()+"，"+w5.getAge()+"岁，唱的歌："+w5.getSong().getName());
    }
}
