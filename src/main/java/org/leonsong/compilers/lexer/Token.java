package org.leonsong.compilers.lexer;

/**
 * @author: Leon Song
 * @date: 2019/12/13
 */
public class Token {

    public final int tagIndex;

    public Token(int tagIndex) {
        this.tagIndex = tagIndex;
    }

    public String toString() {
        return String.format("< %d , %c >", tagIndex, tagIndex);
    }
}
