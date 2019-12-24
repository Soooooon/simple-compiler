package org.leonsong.compilers.inter;

/**
 * 一个语句序列
 *
 * @author: Leon Song
 * @date: 2019/12/24
 */
public class Seq extends Stmt {
    Stmt stmt1, stmt2;

    public Seq(Stmt stmt1, Stmt stmt2) {
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    @Override
    public void gen(int b, int a) {
        if (stmt1 == Stmt.NULL) {
            stmt2.gen(b, a);
        } else if (stmt2 == Stmt.NULL) {
            stmt1.gen(b, a);
        } else {
            int label = newlabel();

            stmt1.gen(b, label);

            emitlabel(label);
            stmt2.gen(label, a);
        }

    }
}
