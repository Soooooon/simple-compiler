package org.leonsong.compilers.symbols;

import org.leonsong.compilers.inter.Id;
import org.leonsong.compilers.lexer.Token;

import java.util.HashMap;

/**
 * 将字符串词法单元映射为类Id的对象
 *
 * @author: Leon Song
 * @date: 2019/12/23
 */
public class Env {
    private HashMap<Token, Id> map;
    protected Env preEnv;

    public Env(Env preEnv) {
        map = new HashMap();
        this.preEnv = preEnv;
    }

    public void put(Token token, Id id) {
        map.put(token, id);
    }

    public Id get(Token token) {
        for (Env env = this; env != null; env = env.preEnv) {
            Id found = env.map.get(token);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

}
