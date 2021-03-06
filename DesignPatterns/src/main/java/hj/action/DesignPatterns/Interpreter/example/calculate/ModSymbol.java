package hj.action.DesignPatterns.Interpreter.example.calculate;

/**
 * <p>Title : 乘法</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-20 </p>
 *
 * @author : hejie
 */
public class ModSymbol extends SymbolElement {
    public ModSymbol(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return left.interpret() % right.interpret();
    }
}
