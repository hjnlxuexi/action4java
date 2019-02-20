package hj.action.DesignPatterns.Composite.example.file;

/**
 * <p>Title : 图片文件</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class ImageFile extends File {
    public ImageFile(String name) {
        super(name);
    }

    public void display() {
        System.out.println("这是图片文件："+ this.getName());
    }

    /**
     * 是否文件夹
     */
    public boolean isFolder(){
        return false;
    }

    public void add(File file) {
        throw new RuntimeException("无法添加子元素...");
    }

    public void remove(File file){
        throw new RuntimeException("非法操作...");
    }
}
