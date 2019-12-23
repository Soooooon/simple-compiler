package org.leonsong.compilers.inter;

import org.leonsong.compilers.lexer.Lexer;

/**
 * @author: Leon Song
 * @date: 2019/12/23
 */
public class Node {
    int lexline = 0;

    Node() {
        lexline = Lexer.line;
    }

    void error(String str) {
        throw new Error(String.format("near line %d: %s", lexline, str));
    }

    /**
     * 跳转位置
     */
    static int labels = 0;

    public int newlabel() {
        return ++labels;
    }

    public void emitlabel(int index) {
        System.out.print(String.format("L%d:", index));
    }

    public void emit(String str) {
        System.out.println("\t" + str);
    }

}
