package hj.action.DesignPatterns.Composite.example.file;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : 文件夹</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class Folder extends File {
    private List<File> files;

    public Folder(String name) {
        super(name);
        files = new ArrayList<>();
    }

    public void display() {
        System.out.println("这是文件夹："+this.getName());
        for (File file : files) {
            file.display();
        }
    }

    /**
     * 是否文件夹
     */
    public boolean isFolder(){
        return true;
    }

    public void add(File file) {
        files.add(file);
    }

    public void remove(File file){
        files.remove(file);
    }
}
