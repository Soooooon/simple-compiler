package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class And extends Logical {
    public And(Token op, Expr expr1, Expr expr2) {
        super(op, expr1, expr2);
    }

    public void jumping(int t, int f) {
        int label = f != 0 ? f : newlabel();
        expr1.jumping(0, label);
        expr2.jumping(t, f);
        if (f == 0) {
            emitlabel(label);
        }
    }
}
