package org.leonsong.compilers.inter;

import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Set extends Stmt {
    public Id id;
    public Expr expr;

    public Set(Id id, Expr expr) {
        this.id = id;
        this.expr = expr;

        if (check(id.type, expr.type) == null) {
            error("type error");
        }
    }

    public Type check(Type type1, Type type2) {
        if (Type.numeric(type1) && Type.numeric(type2)) {
            return type2;
        } else if (type1 == Type.BOOLEAN && type2 == Type.BOOLEAN) {
            return type2;
        } else {
            return null;
        }
    }

    public void gen(int b, int a) {
        emit(toString());
    }

    @Override
    public String toString() {
        return id.toString() + " = " + expr.gen().toString();
    }
}
