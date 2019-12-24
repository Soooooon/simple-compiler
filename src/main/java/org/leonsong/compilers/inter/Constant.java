package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Num;
import org.leonsong.compilers.lexer.Token;
import org.leonsong.compilers.lexer.Word;
import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Constant extends Expr {

    public Constant(Token op, Type type) {
        super(op, type);
    }

    public Constant(int value) {
        this(new Num(value), Type.INT);
    }

    public static final Constant
            TRUE = new Constant(Word.TRUE, Type.BOOLEAN),
            FALSE = new Constant(Word.FALSE, Type.BOOLEAN);

    public void jumping(int t, int f) {
        if (this == TRUE && t != 0) {
            emit("goto L" + t);
        } else if (this == FALSE && f != 0) {
            emit("goto L" + f);
        }
    }
}
