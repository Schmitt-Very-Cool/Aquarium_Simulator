package GUI;

import javax.swing.*;
import FishModels.Fish;
import FishModels.Main;

public class FishMover extends SwingWorker {
    int fromx;
    int fromy;
    int tox;
    int toy;
    int time;
    Fish fish;

    public FishMover(Fish f, int x1, int y1, int x2, int y2, int t){
        fromx = x1;
        fromy = y1;
        tox = x2;
        toy = y2;
        time = t;
        fish = f;
    }

    @Override
    protected Object doInBackground() throws Exception {
        int r = time/20;
        for(int i = 1; i < r; i++) {
            fish.loc.x = fromx + ((tox - fromx)*i/r);
            fish.loc.y = fromy + ((toy - fromy)*i/r);
            Thread.sleep(10);
            Main.gui.tank.repaint();
        }
        fish.loc.x = tox;
        fish.loc.y = toy;
        return null;
    }
}
