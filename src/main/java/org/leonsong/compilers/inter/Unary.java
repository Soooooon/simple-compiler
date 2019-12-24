package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;
import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Unary extends Op {

    public Expr expr;

    public Unary(Token token, Expr expr) {
        super(token, null);
        this.expr = expr;
        type = Type.max(Type.INT, expr.type);
        if (type == null) {
            error("type error");
        }
    }

    public Expr gen() {
        return new Unary(op, expr.reduce());
    }

    @Override
    public String toString() {
        return op.toString() + " " + expr.toString();
    }
}
