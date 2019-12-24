package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Or extends Logical {
    public Or(Token op, Expr expr1, Expr expr2) {
        super(op, expr1, expr2);
    }

    /**
     * 或语句   e.g. B=B1||B2
     *
     * @param t
     * @param f
     */
    public void jumping(int t, int f) {
        int label = t != 0 ? t : newlabel();
        expr1.jumping(label, 0);// 0表示继续下一条语句,即执行B2
        expr2.jumping(t, f);
        if (t == 0) {
            emitlabel(label);
        }
    }
}
