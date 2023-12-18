package GUI;

import FishModels.FishTank;
import FishModels.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecorPanel extends JPanel {
    JButton back = new JButton("Back to Tank");
    JButton leafy = new JButton("Leafy Plants");
    JButton spiny = new JButton("Spiny Plants");
    JButton rocky = new JButton("Rocks");
    JButton skull = new JButton("Skull");
    JButton colosseum = new JButton("Colosseum");
    JButton shipwreck = new JButton("Shipwreck");
    JButton frog = new JButton("Frog");
    JButton chest = new JButton("Treasure Chest");
    JButton scuba = new JButton("Scuba Diver");
    JButton disco = new JButton("Disco Ball");
    JButton purchase = new JButton("Purchase");
    JLabel price = new JLabel ("$0");
    JPanel checkout = new JPanel();
    JPanel catalog = new JPanel();
    JPanel backPanel = new JPanel();
    JPanel centerpiecePanel = new JPanel();
    JLabel centerpiece = new JLabel("Centerpieces:");
    JPanel backgroundPanel = new JPanel();
    JLabel background = new JLabel("Backgrounds:");
    JPanel bubblerPanel = new JPanel();
    JLabel bubbler = new JLabel("Bubblers:");
    JPanel extrasPanel = new JPanel();
    JLabel extras = new JLabel("Extras:");

    String selected = "none";
    int cost = 0;
    public DecorPanel() {
        purchase.setEnabled(true);

        setPreferredSize(new Dimension(1000, 600));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.BOTH;

        backPanel.setLayout(new GridBagLayout());
        gbc.weightx = 1;
        gbc.weighty = 1;
        backPanel.add(back, gbc);

        backgroundPanel.setLayout(new GridBagLayout());
        gbc.gridy = 0;
        backgroundPanel.add(background,gbc);
        gbc.gridy++;
        backgroundPanel.add(leafy,gbc);
        backgroundPanel.add(spiny,gbc);
        backgroundPanel.add(rocky,gbc);
        backgroundPanel.setBorder(new LineBorder(Color.BLACK,1));

        centerpiecePanel.setLayout(new GridBagLayout());
        centerpiecePanel.add(centerpiece,gbc);
        gbc.gridy++;
        centerpiecePanel.add(skull,gbc);
        centerpiecePanel.add(colosseum,gbc);
        centerpiecePanel.add(shipwreck,gbc);
        centerpiecePanel.setBorder(new LineBorder(Color.BLACK,1));

        bubblerPanel.setLayout(new GridBagLayout());
        bubblerPanel.add(bubbler,gbc);
        gbc.gridy++;
        bubblerPanel.add(frog,gbc);
        bubblerPanel.add(chest,gbc);
        bubblerPanel.setBorder(new LineBorder(Color.BLACK,1));

        extrasPanel.setLayout(new GridBagLayout());
        extrasPanel.add(extras, gbc);
        gbc.gridy++;
        extrasPanel.add(scuba, gbc);
        extrasPanel.add(disco,gbc);

        catalog.setLayout(new GridBagLayout());
        gbc.gridy = 0;
        catalog.add(backgroundPanel, gbc);
        gbc.gridy = 1;
        catalog.add(centerpiecePanel, gbc);
        gbc.gridy = 2;
        catalog.add(bubblerPanel, gbc);
        gbc.gridy = 3;
        catalog.add(extrasPanel, gbc);

        gbc.gridy = 0;
        checkout.setLayout(new GridBagLayout());
        checkout.add(price, gbc);
        gbc.weightx = 0;
        checkout.add(purchase, gbc);

        setLayout(new BorderLayout());
        add(backPanel, "North");
        add(catalog, "Center");
        add(checkout, "South");

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gui.swapToTank();
            }
        });

        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected.equals("none")) {
                    return;
                } else {
                    int region;
                    int type;
                    if(selected.equals("leafy")){
                        region = 0;
                        type = 1;
                    }else if(selected.equals("spiny")){
                        region = 0;
                        type = 2;
                    }else if(selected.equals("rocky")){
                        region = 0;
                        type = 3;
                    }else if(selected.equals("skull")){
                        region = 1;
                        type = 1;
                    }else if(selected.equals("colosseum")){
                        region = 1;
                        type = 2;
                    }else if(selected.equals("shipwreck")){
                        region = 1;
                        type = 3;
                    }else if(selected.equals("frog")){
                        region = 2;
                        type = 1;
                    }else if(selected.equals("chest")){
                        region = 2;
                        type = 2;
                    }else if(selected.equals("scuba")){
                        region = 3;
                        type = 0;
                    }else{
                        region = 3;
                        type = 1;
                    }
                    FishTank.addDecor(region, type);
                    FishTank.money -= cost;
                    cost = 0;
                    selected = "none";
                    price.setText("$0");
                    purchase.setEnabled(false);
                    Main.gui.swapToTank();
                    Main.gui.tank.repaint();
                }
            }
        });

        leafy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "leafy";
                cost = 5;
                price.setText("$5");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        spiny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "spiny";
                cost = 5;
                price.setText("$5");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        rocky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "rocky";
                cost = 10;
                price.setText("$10");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        skull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "skull";
                cost = 15;
                price.setText("$15");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        colosseum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "colosseum";
                cost = 20;
                price.setText("$20");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        shipwreck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "shipwreck";
                cost = 15;
                price.setText("$15");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        frog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "frog";
                cost = 15;
                price.setText("$15");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        chest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "chest";
                cost = 15;
                price.setText("$15");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        scuba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "scuba";
                cost = 10;
                price.setText("$10");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        disco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "disco";
                cost = 10;
                price.setText("$10");
                if(cost > FishTank.money){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
    }
}
