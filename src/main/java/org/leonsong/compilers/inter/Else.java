package org.leonsong.compilers.inter;

import org.leonsong.compilers.symbols.Type;

/**
 * 带else分支的if语句
 *
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Else extends Stmt {
    Expr expr;
    Stmt stmt1, stmt2;

    public Else(Expr expr, Stmt stmt1, Stmt stmt2) {
        this.expr = expr;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;

        if (expr.type != Type.BOOLEAN) {
            expr.error("boolean required in if");
        }

    }

    public void gen(int b, int a) {
        int label1 = newlabel();
        int label2 = newlabel();
        expr.jumping(0, label2);

        emitlabel(label1);
        stmt1.gen(label1, a);
        emit("goto L" + a);

        emitlabel(label2);
        stmt2.gen(label2, a);

    }


}
