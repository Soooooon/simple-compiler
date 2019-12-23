package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Token;
import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/23
 */
public class Expr extends Node {
    public Token token;
    public Type type;

    Expr(Token token, Type type) {
        this.token = token;
        this.type = type;
    }
}
