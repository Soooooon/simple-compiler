package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;
import org.leonsong.compilers.symbols.Array;
import org.leonsong.compilers.symbols.Type;

/**
 * 运算符 e.g. <,<=,>=,>,==,!=
 *
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Rel extends Logical {
    public Rel(Token op, Expr expr1, Expr expr2) {
        super(op, expr1, expr2);
    }

    public Type check(Type type1, Type type2) {
        if (type1 instanceof Array || type2 instanceof Array) {
            return null;
        } else if (type1 == type2) {
            return Type.BOOLEAN;
        } else {
            return null;
        }
    }

    public void jumping(int t, int f) {
        Expr left = expr1.reduce();
        Expr right = expr2.reduce();
        String str = left.toString() + " " + op.toString() + " " + right.toString();
        emitJumps(str, t, f);
    }

    @Override
    public String toString() {
        return expr1.toString() + " " + op.toString() + " " + expr2.toString();
    }
}
