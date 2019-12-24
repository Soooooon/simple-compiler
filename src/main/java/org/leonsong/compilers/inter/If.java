package org.leonsong.compilers.inter;

import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class If extends Stmt {
    Expr expr;
    Stmt stmt;

    public If(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
        if (expr.type != Type.BOOLEAN) {
            expr.error("boolean required in if");
        }
    }

    public void gen(int b, int a) {
        int label = newlabel();
        expr.jumping(0, a);// if语句中判断为正，则进入，为假则跳过
        emitlabel(label);
        stmt.gen(label, a);
    }
}
