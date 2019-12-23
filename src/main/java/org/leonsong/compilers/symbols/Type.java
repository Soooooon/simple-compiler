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

    /**
     * 判断类型是否可以转成数字
     *
     * @param type
     * @return
     */
    public static boolean numeric(Type type) {
        if (type == Type.CHAR || type == Type.INT || type == Type.FLOAT) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断类型大小，可用于类型转换中
     *
     * @param t1
     * @param t2
     * @return
     */
    public static Type max(Type t1, Type t2) {
        if (!numeric(t1) || !numeric(t2)) {
            return null;
        } else if (t1 == Type.FLOAT || t2 == Type.FLOAT) {
            return Type.FLOAT;
        } else if (t1 == Type.INT || t2 == Type.INT) {
            return Type.INT;
        } else {
            return Type.CHAR;
        }
    }


}
