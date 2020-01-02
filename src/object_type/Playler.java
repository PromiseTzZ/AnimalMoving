package object_type;

import java.io.*;


/*
    玩家类
    记录玩家信息
 */

public class Playler implements Serializable, Comparable {
    String name;
    int time;

    public Playler(String name, int t) {
        this.name = name;
        time = t;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Object b) {
        Playler p = (Playler) b;
        if ((this.time - p.time) == 0)
            return 1;
        else
            return (this.time - p.time);
    }
}