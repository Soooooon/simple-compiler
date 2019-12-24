package org.leonsong.compilers.inter;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Stmt extends Node {
    public Stmt() {
        //　啥都不做，放到子类中处理
    }

    public static Stmt NULL = new Stmt();

    /**
     * @param b 语句开始出的标号
     * @param a 语句下一条指令的标号
     */
    public void gen(int b, int a) {
    }

    int after = 0;// 保存语句的下一条指令的标号

    public static Stmt Enclosing = Stmt.NULL;// 用于break语句
}
