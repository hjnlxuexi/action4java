package hj.action.DesignPatterns.Composite.example.file;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class Main {

    public static void main(String[] args) {
        File textFile = new TextFile("T");
        File imageFile = new ImageFile("I");
        File video = new VideoFile("V");

        File folder = new Folder("F");

        File top = new Folder("T");

        top.add(textFile);
        top.add(folder);

        folder.add(imageFile);
        folder.add(video);

        //显示所有文件
        top.display();
    }
}
