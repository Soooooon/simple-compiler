package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Word;
import org.leonsong.compilers.symbols.Type;

/**
 * 临时变量
 *
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Temp extends Expr {

    static int count = 0;

    int number = 0;

    Temp(Type type) {
        super(Word.TEMP, type);
        number = ++count;
    }

    @Override
    public String toString() {
        return "t" + number;
    }
}
