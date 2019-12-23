package org.leonsong.compilers.symbols;

import org.leonsong.compilers.lexer.Tag;

/**
 * @author: Leon Song
 * @date: 2019/12/23
 */
public class Array extends Type {
    /**
     * 数组类型
     */
    public Type of;
    /**
     * 数组长度，默认长度为1
     */
    public int size = 1;

    public Array(int size, Type type) {
        super("[]", Tag.INDEX.getIndex(), size * type.width);
        this.size = size;
        this.of = type;
    }

    @Override
    public String toString() {
        return "[" + size + "]" + of.toString();
    }
}
