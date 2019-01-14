package hj.action.basics.InnerClass;

/**
 * <p>Title : 内部类的用法</p>
 * <p>Description :
 * 成员内部类: 依附于外部类的实例对象
 *
 * 局部内部类：作用域在代码块
 *
 * 匿名内部类：特殊的局部内部类  http://cmsblogs.com/?p=68
 *
 * 静态内部类：嵌套内部类
 *      它的创建是不需要依赖于外围类的
 *      它不能使用任何外围类的非static成员变量和方法
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class Outer {
    private String name;
    private int age;
    private static int cnt;
    public String getName() {
        return name;
    }

    public void show(){
        System.out.println("name:"+name+",age:"+age);
    }

    public MemberInner getInner(){
        return new MemberInner();
    }


    /**
     * 成员内部类
     */
    private class MemberInner {
        MemberInner(){
            //共享成员变量
            age = 10;
            name = "hj";
        }
        public void show(){
            System.out.println("name:"+getName()+",age:"+age);
        }

        /**
         * 获取外部类，
         * 使用 Outer.this
         * @return
         */
        public Outer getOuter(){
            return Outer.this;
        }
    }

    /**
     * 局部内部类
     * @param i 是只读的final    不能被修改，当修改时编译器会报错
     */
    public void showPartInner(int i){
        class PartInner {
            private String name;
            PartInner(){
                //访问外部类
                Outer.this.name = "Outer";
                age = 11;

                this.name = "PartInner"+i;
            }
        }
        PartInner partInner = new PartInner();
        System.out.println(partInner.name);
    }

    /**
     * 匿名内部类
     * @param i 是只读的final    不能被修改，当修改时编译器会报错
     *          无需手动加final关键字，是隐含的
     * @return
     */
    public Anonymous getAnonymousInner(int i){
        int j = 5;
        return new Anonymous() {
            @Override
            public int count() {
                age++;//使用外部类的变量
                System.out.println("AnonymousInner"+i+j);
                return age;
            }
        };
    }

    /**
     * 接口
     * 用来实现匿名内部类
     */
    interface Anonymous {
        int count();
    }

    /**
     * 静态内部类：嵌套类
     */
    static class StaticInner {
        private int num;
        private static String name_ = "StaticInner";
        StaticInner(){
            //不能访问非静态变量
            //Outer.this.age = 13;
            //age = 13;

            num = 100;
            cnt++;
            System.out.println(name_+","+num+","+cnt);
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        // .new 的用法
        MemberInner memberInner = outer.new MemberInner();
        // 推荐使用get方式获取内部类实例
        MemberInner memberInner1 = outer.getInner();
        memberInner.show();
        memberInner.getOuter().show();


        //局部内部类
        outer.showPartInner(3);

        //匿名内部类
        Anonymous anonymous = outer.getAnonymousInner(3);
        System.out.println(anonymous.count());


        //静态内部类
        StaticInner staticInner = new StaticInner();

    }
}
