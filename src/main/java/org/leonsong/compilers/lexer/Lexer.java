package org.leonsong.compilers.lexer;

import org.leonsong.compilers.symbols.Type;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author: Leon Song
 * @date: 2019/12/14
 */
public class Lexer {
    public static int line = 1;

    char peek = ' ';

    HashMap<String, Word> words = new HashMap<String, Word>();

    private void reserve(Word word) {
        words.put(word.lexeme, word);
    }

    public Lexer() {
        /**
         * 选定的保留字
         */
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        /**
         * 其他地方定义的对象的词素
         */
        reserve(Word.TRUE);
        reserve(Word.FALSE);
        reserve(Type.INT);
        reserve(Type.CHAR);
        reserve(Type.BOOLEAN);
        reserve(Type.FLOAT);
    }

    private void readch() throws IOException {
        peek = (char) System.in.read();
    }

    private boolean readch(char c) throws IOException {
        readch();
        if (peek != c) {
            return false;
        }
        peek = ' ';
        return true;
    }


    public Token scan() throws IOException {
        // 跳过空格
        for (; ; readch()) {
            if (peek == ' ' || peek == '\t') {
                continue;
            } else if (peek == '\n') {
                line++;
            } else {
                break;
            }
        }

        // 识别复合词法单元
        switch (peek) {
            case '&':
                if (readch('&')) {
                    return Word.AND;
                } else {
                    return new Token('&');
                }
            case '|':
                if (readch('|')) {
                    return Word.OR;
                } else {
                    return new Token('|');
                }
            case '=':
                if (readch('=')) {
                    return Word.EQ;
                } else {
                    return new Token('=');
                }
            case '!':
                if (readch('=')) {
                    return Word.NE;
                } else {
                    return new Token('!');
                }
            case '<':
                if (readch('=')) {
                    return Word.LE;
                } else {
                    return new Token('<');
                }
            case '>':
                if (readch('=')) {
                    return Word.GE;
                } else {
                    return new Token('>');
                }
        }

        // 识别数字
        if (Character.isDigit(peek)) {
            int value = 0;

            while (Character.isDigit(peek)) {
                value = 10 * value + Character.digit(peek, 10);
                readch();
            }

            //　如果不含小数点
            if (peek != '.') {
                return new Num(value);
            }

            float real = value;
            float d = 10;

            for (; ; ) {
                readch();
                if (!Character.isDigit(peek)) {
                    break;
                }
                real = real + Character.digit(peek, 10) / d;
                d *= 10;
            }

            return new Real(real);
        }

        //　识别字符串
        if (Character.isLetter(peek)) {
            StringBuffer sb = new StringBuffer();

            while (Character.isLetterOrDigit(peek)) {
                sb.append(peek);
                readch();
            }

            String str = sb.toString();

            Word word = words.get(str);
            if (null != word) {
                return word;
            } else {
                word = new Word(str, Tag.ID);
                words.put(str, word);
                return word;
            }

        }

        Token token = new Token(peek);
        peek = ' ';
        return token;

    }


    /**
     * 用于测试
     */
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        try {
            Token token = lexer.scan();
            System.out.println(token.toString());
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

}
