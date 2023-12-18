package GUI;

import FishModels.FishTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GUI extends JFrame {
    public TankPanel tank;

    {
        try {
            tank = new TankPanel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    StatsPanel stats = new StatsPanel();
    ControlPanel controlPanel = new ControlPanel();
    FishStorePanel fishStore = new FishStorePanel();
    DecorPanel decorStore = new DecorPanel();

    public GUI(){
        setTitle("Aquarium Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(tank, "Center");
        add(controlPanel, "South");
        add(stats, "West");
        pack();
    }

    public void swapToFishStore(){
        remove(controlPanel);
        remove(tank);
        remove(stats);
        add(fishStore);
        pack();
    }

    public void  swapToTank(){
        remove(fishStore);
        remove(decorStore);
        add(tank, "Center");
        add(controlPanel, "South");
        add(stats, "West");
        pack();
    }

    public void swapToDecorStore(){
        remove(controlPanel);
        remove(tank);
        remove(stats);
        add(decorStore);
        pack();
    }

    public void updateMetrics(){

        controlPanel.money.setText("$" + String.valueOf(FishTank.money));
        controlPanel.dirtyBar.setValue(FishTank.dirtiness);
        controlPanel.hungryBar.setValue(FishTank.hunger);
    }

    public void updateHappiness(String f, int h){
        if(stats.selectedFish != null && !stats.selectedFish.name.equals(f)) {
            return;
        }else{
            stats.happyBar.setValue(h);
        }
    }
}
