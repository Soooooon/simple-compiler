package org.leonsong.compilers.lexer;

/**
 * @author: Leon Song
 * @date: 2019/12/13
 */
public class Num extends Token {

    public final int value;

    public Num(int value) {
        super(Tag.NUM);
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }

}
