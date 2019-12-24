package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Not extends Logical {
    public Not(Token op, Expr expr2) {
        super(op, expr2, expr2);
    }

    public void jumping(int t, int f) {
        expr2.jumping(f, t);
    }

    @Override
    public String toString() {
        return op.toString() + " " + expr2.toString();
    }
}
