package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;
import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/23
 */
public class Expr extends Node {
    /**
     * 运算符
     */
    public Token op;
    /**
     * 类型
     */
    public Type type;

    Expr(Token op, Type type) {
        this.op = op;
        this.type = type;
    }

    /**
     * 返回一个项，成为三地址指令的右部
     *
     * @return
     */
    public Expr gen() {
        return this;
    }

    /**
     * 将表达式计算归约为一个单一的地址
     *
     * @return
     */
    public Expr reduce() {
        return this;
    }

    public void jumping(int t, int f) {
        emitJumps(toString(), t, f);
    }

    public void emitJumps(String str, int t, int f) {
        if (t != 0 && f != 0) {
            emit(String.format("if %s goto L%d", str, t));
            emit(String.format("goto L%d", f));
        } else if (t != 0) {
            emit(String.format("if %s goto L%d", str, t));
        } else if (f != 0) {
            emit(String.format("if false %s goto L%d", str, f));
        }
    }


    @Override
    public String toString() {
        return op.toString();
    }
}
