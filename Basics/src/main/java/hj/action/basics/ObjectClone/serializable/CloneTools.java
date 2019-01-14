package hj.action.basics.ObjectClone.serializable;

import java.io.*;

/**
 * <p>Title : 深拷贝</p>
 * <p>Description :
 * 利用序列化，在内存中通过字节流的拷贝实现；
 * 把母对象写入到一个字节流中，再从字节流中将其读出来，这样就可以创建一个新的对象了，
 * 并且该新对象与母对象之间并不存在引用共享的问题，真正实现对象的深拷贝。
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class CloneTools {
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone (T obj) {
        T newObj;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ByteArrayOutputStream bos = null;
        ByteArrayInputStream bis = null;
        try {
            //1、将原对象写入流
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);

            //2、将流转换为对象
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            newObj = (T) ois.readObject();

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return newObj;
    }
}
