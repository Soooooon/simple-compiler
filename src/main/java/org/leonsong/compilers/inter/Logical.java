package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;
import org.leonsong.compilers.symbols.Type;

/**
 * 逻辑运算符的父类
 *
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Logical extends Expr {
    public Expr expr1, expr2;

    Logical(Token op, Expr expr1, Expr expr2) {
        super(op, null);
        this.expr1 = expr1;
        this.expr2 = expr2;
        type = check(expr1.type, expr2.type);
        if (type == null) {
            error("type error");
        }
    }

    public Type check(Type type1, Type type2) {
        if (type1 == Type.BOOLEAN && type2 == Type.BOOLEAN) {
            return Type.BOOLEAN;
        } else {
            return null;
        }
    }

    public Expr gen() {
        int f = newlabel();
        int a = newlabel();
        Temp temp = new Temp(type);
        jumping(0, f);
        emit(temp.toString() + " = true");
        emit("goto L" + a);
        emitlabel(f);
        emit(temp.toString() + " = false");
        emitlabel(a);
        return temp;
    }

    @Override
    public String toString() {
        return expr1.toString() + " " + op.toString() + " " + expr2.toString();
    }
}
