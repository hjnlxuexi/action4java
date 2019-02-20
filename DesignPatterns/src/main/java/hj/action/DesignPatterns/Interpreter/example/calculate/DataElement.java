package hj.action.DesignPatterns.Interpreter.example.calculate;

/**
 * <p>Title : 数据元素</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-20 </p>
 *
 * @author : hejie
 */
public class DataElement implements Element {
    private int value;

    public DataElement(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return value;
    }
}
