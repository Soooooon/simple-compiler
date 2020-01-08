package org.leonsong.compilers.lexer;

/**
 * @author: Leon Song
 * @date: 2019/12/14
 * <p>
 * Word类用于管理关键字、标识符、&&类似的复合词法单元的词素
 */
public class Word extends Token {

    public String lexeme = "";

    public Word(String lexeme, int tagIndex) {
        super(tagIndex);
        this.lexeme = lexeme;
    }

    public String toString() {
        return lexeme;
    }

    public static final Word
            AND = new Word("&&", Tag.AND), OR = new Word("||", Tag.OR),
            EQ = new Word("==", Tag.EQ), NE = new Word("!=", Tag.NE),
            LE = new Word("<=", Tag.LE), GE = new Word(">=", Tag.GE),
            MINUS = new Word("minus", Tag.MINUS),
            TRUE = new Word("true", Tag.TRUE),
            FALSE = new Word("false", Tag.FALSE),
            TEMP = new Word("t", Tag.TEMP);
}
