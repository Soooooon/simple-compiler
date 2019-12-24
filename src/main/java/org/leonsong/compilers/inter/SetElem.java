package org.leonsong.compilers.inter;

import org.leonsong.compilers.symbols.Array;
import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class SetElem extends Stmt {
    public Id array;
    public Expr index;
    public Expr expr;

    public SetElem(Access access, Expr expr) {
        this.array = access.array;
        this.index = access.index;
        this.expr = expr;

        if (check(index.type, expr.type) == null) {
            error("type error");
        }
    }

    public Type check(Type type1, Type type2) {
        if (type1 instanceof Array || type2 instanceof Array) {
            return null;
        } else if (type1 == type2) {
            return type2;
        } else if (Type.numeric(type1) && Type.numeric(type2)) {
            return type2;
        } else {
            return null;
        }
    }

    public void gen(int b, int a) {
        String str1 = index.reduce().toString();
        String str2 = expr.reduce().toString();

        emit(array.toString() + " [ " + str1 + " ] " + str2);
    }
}
