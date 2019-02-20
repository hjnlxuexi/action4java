package hj.action.DesignPatterns.Composite.example.file;

/**
 * <p>Title : 文件</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public abstract class File {

    private String name;

    public File(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public abstract void display();

    /**
     * 是否文件夹
     */
    public abstract boolean isFolder();

    public abstract void add(File file);

    public abstract void remove(File file);
}
