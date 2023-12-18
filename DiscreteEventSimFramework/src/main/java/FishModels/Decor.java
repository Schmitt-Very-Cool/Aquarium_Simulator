package FishModels;

import java.awt.*;

public class Decor {
    public static final int DC_SCUBA = 0;
    public static final int DC_DISCO = 1;

    public Point loc;
    public int type;
    public int w;
    public int h;

    public Decor(int t){
        type = t;
        h = 50;
        if(t == DC_SCUBA){
            w = 100;
        }else{
            w = 50;
        }
        loc = Main.newLoc(w,h);
    }
}
