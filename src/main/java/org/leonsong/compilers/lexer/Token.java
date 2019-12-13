package org.leonsong.compilers.lexer;

/**
 * @author: Leon Song
 * @date: 2019/12/13
 */
public class Token {

    public final Tag tag;

    public Token(Tag tag) {
        this.tag = tag;
    }

    public String toString() {
        return String.format("< %s >", tag.toString());
    }
}
