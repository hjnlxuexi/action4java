package hj.action.DesignPatterns.Composite.example.file;

/**
 * <p>Title : 视频文件</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class VideoFile extends File {
    public VideoFile(String name) {
        super(name);
    }

    public void display() {
        System.out.println("这是视频文件："+ this.getName());
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
