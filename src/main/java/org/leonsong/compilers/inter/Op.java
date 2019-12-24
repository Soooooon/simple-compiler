package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;
import org.leonsong.compilers.symbols.Type;


/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Op extends Expr {

    public Op(Token op, Type type) {
        super(op, type);
    }

    public Expr reduce() {
        Expr expr = gen();
        Temp t = new Temp(type);
        emit(t.toString() + "=" + expr.toString());
        return t;
    }
}
