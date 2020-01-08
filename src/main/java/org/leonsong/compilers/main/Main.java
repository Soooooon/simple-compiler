package org.leonsong.compilers.main;

import org.leonsong.compilers.lexer.Lexer;
import org.leonsong.compilers.parser.Parser;

import java.io.IOException;

/**
 * @author: Leon Song
 * @date: 2020/1/8
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lex = new Lexer();
        Parser parse = new Parser(lex);
        parse.program();
        System.out.write('\n');
    }
}
