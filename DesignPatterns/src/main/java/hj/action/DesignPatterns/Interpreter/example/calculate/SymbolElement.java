package hj.action.DesignPatterns.Interpreter.example.calculate;

/**
 * <p>Title : 符号元素</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-20 </p>
 *
 * @author : hejie
 */
public abstract class SymbolElement implements Element {
    protected Element left;//左操作数
    protected Element right;//右操作数

    public SymbolElement(Element left, Element right) {
        this.left = left;
        this.right = right;
    }
}
