package org.leonsong.compilers.lexer;


/**
 * @author: Leon Song
 * @date: 2019/12/14
 */
public class Real extends Token {

    public final float value;

    public Real(float value) {
        super(Tag.REAL.getIndex());
        this.value = value;
    }

    public String toString() {
        return String.format("< %d , %f >", tagIndex, value);
    }

}
