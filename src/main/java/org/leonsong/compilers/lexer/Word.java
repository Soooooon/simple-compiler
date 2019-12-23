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
            AND = new Word("&&", Tag.AND.getIndex()), OR = new Word("||", Tag.OR.getIndex()),
            EQ = new Word("==", Tag.EQ.getIndex()), NE = new Word("!=", Tag.NE.getIndex()),
            LE = new Word("<=", Tag.LE.getIndex()), GE = new Word(">=", Tag.GE.getIndex()),
            MINUS = new Word("minus", Tag.MINUS.getIndex()),
            TRUE = new Word("true", Tag.TRUE.getIndex()),
            FALSE = new Word("false", Tag.FALSE.getIndex()),
            TEMP = new Word("t", Tag.TEMP.getIndex());
}
