package org.leonsong.compilers.inter;

import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Do extends Stmt {

    Expr expr;

    Stmt stmt;

    public Do() {
        expr = null;
        stmt = null;
    }

    public void init(Stmt stmt, Expr expr) {
        this.expr = expr;
        this.stmt = stmt;

        if (expr.type != Type.BOOLEAN) {
            expr.error("boolean required in if");
        }
    }

    public void gen(int b, int a) {
        after = a;
        int label = newlabel();

        stmt.gen(b, label);
        emitlabel(label);
        expr.jumping(b, 0);

    }
}
