package org.leonsong.compilers.inter;

import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class While extends Stmt {
    Expr expr;
    Stmt stmt;

    public While() {
        expr = null;
        stmt = null;
    }

    public void init(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;

        if (expr.type != Type.BOOLEAN) {
            expr.error("boolean required in if");
        }
    }

    public void gen(int b, int a) {
        after = a;
        expr.jumping(0, a);
        int label = newlabel();

        emitlabel(label);
        stmt.gen(label, b);
        emit("goto L" + b);
    }

}
