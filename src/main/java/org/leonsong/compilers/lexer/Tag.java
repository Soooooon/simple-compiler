package org.leonsong.compilers.lexer;

import lombok.Getter;

/**
 * @author: Leon Song
 * @date: 2019/12/13
 * <p>
 * Tag类定义各个词法单元对应的常量
 */
public enum Tag {
    AND(256),
    BASIC(257),
    BREAK(258),
    DO(259),
    ELSE(260),
    EQ(261),
    FALSE(262),
    GE(263),
    ID(264),
    IF(265),
    INDEX(266),
    LE(267),
    MINUS(268),
    NE(269),
    NUM(270),
    OR(271),
    REAL(272),
    TEMP(273),
    TRUE(274),
    WHILE(275);

    @Getter
    private int index;

    Tag(int index) {
        this.index = index;
    }

    /**
     * 根据编码返回对应枚举类
     *
     * @param tagIndex
     * @return
     */
    public static Tag getTag(int tagIndex) {
        for (Tag tag : values()) {
            if (tag.index == tagIndex) {
                return tag;
            }
        }
        return null;
    }

}
