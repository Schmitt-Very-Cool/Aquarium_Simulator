package GUI;

import FishModels.Main;

import javax.swing.*;

public class MoneyMotionHandler extends SwingWorker {
    Money money;
    public MoneyMotionHandler(Money m){
        money = m;
    }

    @Override
    protected Object doInBackground() throws Exception {
        while(money.y > 40){
            money.y -= 2;
            Main.gui.tank.repaint();
            Thread.sleep(10);
        }
        Main.gui.tank.monies.remove(money);
        Main.gui.tank.repaint();
        return null;
    }
}
