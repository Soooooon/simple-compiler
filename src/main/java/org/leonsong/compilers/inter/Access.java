package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Tag;
import org.leonsong.compilers.lexer.Word;
import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Access extends Op {
    public Id array;
    /**
     * 数组索引可以是一个布尔表达式
     */
    public Expr index;

    public Access(Id array, Expr index, Type type) {
        super(new Word("[]", Tag.INDEX.getIndex()), type);
        this.array = array;
        this.index = index;
    }

    public Expr gen() {
        return new Access(array, index.reduce(), type);
    }

    public void jumping(int t, int f) {
        emitJumps(reduce().toString(), t, f);
    }

    @Override
    public String toString() {
        return array.toString() + " [ " + index.toString() + " ] ";
    }
}
