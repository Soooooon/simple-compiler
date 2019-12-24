package org.leonsong.compilers.inter;

/**
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Break extends Stmt {
    Stmt stmt;

    public Break() {
        if (Stmt.Enclosing == Stmt.NULL) {
            error("unenclosed break");
        }
        stmt = Stmt.Enclosing;
    }

    @Override
    public void gen(int b, int a) {
        emit("goto L" + stmt.after);
    }
}
