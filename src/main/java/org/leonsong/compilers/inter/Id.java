package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Word;
import org.leonsong.compilers.symbols.Type;

/**
 * @author: Leon Song
 * @date: 2019/12/23
 */
public class Id extends Expr {

    /**
     * 偏移量，相对地址
     */
    public int offset;

    public Id(Word id, Type type, int offset) {
        super(id, type);
        this.offset = offset;
    }
}
