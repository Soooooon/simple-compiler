package org.leonsong.compilers.lexer;

/**
 * @author: Leon Song
 * @date: 2019/12/13
 */
public class Token {

    public final int tag;

    public Token(int tag) {
        this.tag = tag;
    }

    public String toString() {
        return "" + (char) tag;
    }
}
