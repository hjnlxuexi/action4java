package hj.action.basics.ObjectClone.serializable;


import java.io.Serializable;

/**
 * <p>Title : 测试，深拷贝</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class Main {
    static class Person implements Serializable {
        private String name;
        private int age;
        private Song song;

        Person(String name , int age){
            this.name = name;
            this.age = age;
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

    static class Song implements Serializable {
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

    public static void main(String[] args) {
        Song song = new Song("一生所爱");
        Person z3 = new Person("张三", 3);
        z3.setSong(song);

        Person l4 = CloneTools.clone(z3);
        l4.setName("李四");
        l4.setAge(4);

        Person w5 = CloneTools.clone(z3);
        w5.setName("王五");
        w5.setAge(5);
        //深拷贝，为全新的song对象
        w5.getSong().setName("张三的歌");


        System.out.println(z3.getName()+"，"+z3.getAge()+"岁，唱的歌："+z3.getSong().getName());
        System.out.println(l4.getName()+"，"+l4.getAge()+"岁，唱的歌："+l4.getSong().getName());
        System.out.println(w5.getName()+"，"+w5.getAge()+"岁，唱的歌："+w5.getSong().getName());
    }
}
