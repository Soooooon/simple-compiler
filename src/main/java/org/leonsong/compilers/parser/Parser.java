package org.leonsong.compilers.parser;

import org.leonsong.compilers.inter.*;
import org.leonsong.compilers.lexer.*;
import org.leonsong.compilers.symbols.Array;
import org.leonsong.compilers.symbols.Env;
import org.leonsong.compilers.symbols.Type;

import java.io.IOException;

/**
 * 语法分析器
 *
 * @author: Leon Song
 * @date: 2020/1/8
 */
public class Parser {
    /**
     * 语法分析器的词法分析器
     */
    private Lexer lex;

    /**
     * 向前看词法单元
     */
    private Token look;

    /**
     * 当前或顶层的符号表
     */
    Env top = null;

    /**
     * 用于变量声明的存储位置
     */
    int used = 0;

    public Parser(Lexer lex) throws IOException {
        this.lex = lex;
        move();
    }

    void move() throws IOException {
        look = lex.scan();
    }

    void error(String str) {
        throw new Error("near line " + lex.line + ": " + str);
    }

    void match(int t) throws IOException {
        if (look.tag == t) {
            move();
        } else {
            error("syntax error");
        }
    }

    /**
     * 主分析过程
     */
    public void program() throws IOException {
        Stmt s = block();
        int begin = s.newlabel();
        int after = s.newlabel();
        s.emitlabel(begin);
        s.gen(begin, after);
        s.emitlabel(after);
    }

    Stmt block() throws IOException {
        match('{');
        Env savedEnv = top;
        top = new Env(top);

        // 处理声明语句
        decls();
        // 处理执行语句
        Stmt s = stmts();

        match('}');
        top = savedEnv;
        return s;
    }

    /**
     * 处理声明语句
     *
     * @throws IOException
     */
    void decls() throws IOException {
        while (look.tag == Tag.BASIC) {
            // 处理文本
            Type p = type();
            Token tok = look;
            match(Tag.ID);
            match(';');
            // 维护符号表
            Id id = new Id((Word) tok, p, used);
            top.put(tok, id);
            // 维护存储位置
            used = used + p.width;
        }
    }

    /**
     * 处理标识符
     *
     * @return
     */
    Type type() throws IOException {
        Type p = (Type) look;
        match(Tag.BASIC);
        if (look.tag != '[') {
            return p;
        } else {
            return dims(p);
        }
    }

    /**
     * 数组声明类型时的特殊处理 e.g.  int[8]
     *
     * @param p
     * @return
     */
    Type dims(Type p) throws IOException {
        match('[');
        Token tok = look;
        match(Tag.NUM);
        match(']');

        if (look.tag == '[') {
            p = dims(p);
        }
        return new Array(((Num) tok).value, p);// 数组长度, 类型
    }

    /**
     * 处理执行语句
     *
     * @return
     */
    Stmt stmts() throws IOException {
        if (look.tag == '}') {
            return Stmt.NULL;
        } else {
            return new Seq(stmt(), stmts());
        }
    }

    Stmt stmt() throws IOException {
        Expr x;
        Stmt s, s1, s2, savedStmt;

        switch (look.tag) {
            case ';':
                move();
                return Stmt.NULL;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                x = bool();
                match(')');
                s1 = stmt();
                if (look.tag == Tag.ELSE) {
                    match(Tag.ELSE);
                    s2 = stmt();
                    return new Else(x, s1, s2);
                } else {
                    return new If(x, s1);
                }
            case Tag.WHILE:
                While whileNode = new While();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = whileNode;

                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');

                s1 = stmt();
                whileNode.init(x, s1);
                Stmt.Enclosing = savedStmt;
                return whileNode;
            case Tag.DO:
                Do doNode = new Do();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = doNode;

                match(Tag.DO);
                s1 = stmt();
                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');
                match(';');

                doNode.init(s1, x);
                Stmt.Enclosing = savedStmt;
                return doNode;
            case Tag.BREAK:
                match(Tag.BREAK);
                match(';');
                return new Break();
            case '{':
                return block();
            default:
                return assign();
        }

    }

    /**
     * 赋值语句
     *
     * @return
     * @throws IOException
     */
    Stmt assign() throws IOException {
        Stmt stmt;
        Token t = look;

        match(Tag.ID);
        Id id = top.get(t);
        if (id == null) {
            error(t.toString() + " undeclared");
        }
        if (look.tag == '=') {
            move();
            stmt = new Set(id, bool());
        } else {
            Access x = offset(id);
            match('=');
            stmt = new SetElem(x, bool());
        }
        match(';');
        return stmt;
    }

    /**
     * 布尔表达式
     *
     * @return
     */
    Expr bool() throws IOException {
        Expr x = join();
        while (look.tag == Tag.OR) {
            Token tok = look;
            move();
            x = new Or(tok, x, join());
        }
        return x;
    }

    Expr join() throws IOException {
        Expr x = equality();
        while (look.tag == Tag.AND) {
            Token tok = look;
            move();
            x = new And(tok, x, equality());
        }
        return x;
    }

    Expr equality() throws IOException {
        Expr x = rel();
        while (look.tag == Tag.EQ || look.tag == Tag.NE) {
            Token tok = look;
            move();
            x = new Rel(tok, x, rel());
        }
        return x;
    }

    Expr rel() throws IOException {
        Expr x = expr();
        switch (look.tag) {
            case '<':
            case Tag.LE:
            case Tag.GE:
            case '>':
                Token tok = look;
                move();
                return new Rel(tok, x, expr());
            default:
                return x;
        }

    }

    Expr expr() throws IOException {
        Expr x = term();
        while (look.tag == '+' || look.tag == '-') {
            Token tok = look;
            move();
            x = new Arith(tok, x, term());
        }
        return x;
    }

    Expr term() throws IOException {
        Expr x = unary();
        while (look.tag == '*' || look.tag == '/') {
            Token tok = look;
            move();
            x = new Arith(tok, x, unary());
        }
        return x;
    }

    /**
     * 一元运算符
     *
     * @return
     */
    Expr unary() throws IOException {
        if (look.tag == '-') {
            move();
            return new Unary(Word.MINUS, unary());
        } else if (look.tag == '!') {
            Token tok = look;
            move();
            return new Not(tok, unary());
        } else {
            return factor();
        }
    }

    Expr factor() throws IOException {
        Expr x = null;
        switch (look.tag) {
            case '(':
                move();
                x = bool();
                match(')');
                return x;
            case Tag.NUM:
                x = new Constant(look, Type.INT);
                move();
                return x;
            case Tag.REAL:
                x = new Constant(look, Type.FLOAT);
                move();
                return x;
            case Tag.TRUE:
                x = Constant.TRUE;
                move();
                return x;
            case Tag.FALSE:
                x = Constant.FALSE;
                move();
                return x;
            case Tag.ID:
                String s = look.toString();
                Id id = top.get(look);
                if (id == null) {
                    error(look.toString() + " undeclared");
                } else {
                    move();
                    if (look.tag != '[') {
                        return id;
                    } else {
                        return offset(id);
                    }
                }
            default:
                error("syntax error");
                return x;
        }
    }

    /**
     * I -> [E] | [E]I
     *
     * @param a
     * @return
     */
    Access offset(Id a) throws IOException {
        Expr i;
        Expr w;
        Expr t1, t2;
        Expr loc;

        Type type = a.type;
        match('[');
        i = bool();
        match(']');

        type = ((Array) type).of;
        w = new Constant(type.width);
        t1 = new Arith(new Token('*'), i, w);
        loc = t1;

        while (look.tag == '[') {
            match('[');
            i = bool();
            match(']');
            type = ((Array) type).of;
            w = new Constant(type.width);
            t1 = new Arith(new Token('*'), i, w);
            t2 = new Arith(new Token('+'), loc, t1);
            loc = t2;
        }

        return new Access(a, loc, type);
    }


}
