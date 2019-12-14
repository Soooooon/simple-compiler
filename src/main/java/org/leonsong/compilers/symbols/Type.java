package org.leonsong.compilers.symbols;

import org.leonsong.compilers.lexer.Tag;
import org.leonsong.compilers.lexer.Word;

/**
 * @author: Leon Song
 * @date: 2019/12/14
 */
public class Type extends Word {

    public int width = 0;

    public Type(String lexeme, int tagIndex, int width) {
        super(lexeme, tagIndex);
        this.width = width;
    }

    public static final Type
            INT = new Type("int", Tag.BASIC.getIndex(), 4),
            FLOAT = new Type("float", Tag.BASIC.getIndex(), 8),
            CHAR = new Type("char", Tag.BASIC.getIndex(), 1),
            BOOLEAN = new Type("boolean", Tag.BASIC.getIndex(), 1);
}
