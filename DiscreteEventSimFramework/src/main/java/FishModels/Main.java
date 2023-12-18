package FishModels;

import DESF.DiscreteEventSim;
import GUI.GUI;
import jdk.jfr.Event;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static GUI gui;

    public static void main(String[] args) throws IOException, InterruptedException {

        gui = new GUI();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);
            }
        });

        Fish f = new Fish("Slimy", 0);
        FishTank.addFish(f);
        DiscreteEventSim des = new DiscreteEventSim();
        FishTank.startupFishTank();
        DiscreteEventSim.addModel(f, "TankWideEventModel", false);

        des.run();

    }

    public static Point newLoc(int w, int h){
        return gui.tank.newLoc(w, h);
    }
}
