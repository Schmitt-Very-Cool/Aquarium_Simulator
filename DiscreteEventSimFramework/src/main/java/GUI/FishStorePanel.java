package GUI;

import FishModels.FishTank;
import FishModels.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FishStorePanel extends JPanel {
    JButton back = new JButton("Back to Tank");
    JButton goldfish = new JButton("Goldfish");
    JButton plecostomus = new JButton("Plecostomus");
    JButton fishWithHat = new JButton("Fish With Hat");
    JButton cichlid = new JButton("Cichlid");
    JButton neonTetra = new JButton("Neon Tetra");
    JButton betta = new JButton("Betta");
    JButton snail = new JButton("Snail");
    JButton discus = new JButton("Discus");
    JButton platy = new JButton("Platy");
    JButton angelfish = new JButton("Angelfish");
    JButton purchase = new JButton("Purchase");
    JLabel price = new JLabel ("$0");
    JPanel checkout = new JPanel();
    JPanel catalog = new JPanel();
    JPanel backPanel = new JPanel();
    JTextArea nameZone = new JTextArea();

    String selected = "none";
    public FishStorePanel(){

        purchase.setEnabled(false);

        setPreferredSize(new Dimension(1000,600));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.fill = GridBagConstraints.BOTH;

        backPanel.setLayout(new GridBagLayout());
        gbc.weightx = 1;
        gbc.weighty = 1;
        backPanel.add(back,gbc);

        catalog.setLayout(new GridBagLayout());
        gbc.gridy = 0;
        catalog.add(goldfish,gbc);
        catalog.add(plecostomus,gbc);
        gbc.gridy = 1;
        catalog.add(fishWithHat,gbc);
        catalog.add(cichlid,gbc);
        gbc.gridy = 2;
        catalog.add(neonTetra,gbc);
        catalog.add(betta,gbc);
        gbc.gridy = 3;
        catalog.add(snail,gbc);
        catalog.add(discus,gbc);
        gbc.gridy = 4;
        catalog.add(platy,gbc);
        catalog.add(angelfish,gbc);

        gbc.gridy = 0;
        checkout.setLayout(new GridBagLayout());
        checkout.add(price,gbc);
        checkout.add(nameZone,gbc);
        gbc.weightx = 0;
        checkout.add(purchase,gbc);

        gbc.weightx = 1;
        gbc.insets = new Insets(0,0,0,0);
        setLayout(new BorderLayout());
        add(backPanel,"North");
        add(catalog,"Center");
        add(checkout,"South");

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gui.swapToTank();
            }
        });

        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selected.equals("none")){
                    return;
                }else{
                    try {
                        FishTank.addFish(selected, nameZone.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    FishTank.money -= 10;
                    price.setText("$0");
                    selected = "none";
                    nameZone.setText("");
                    purchase.setEnabled(false);
                    Main.gui.swapToTank();
                    Main.gui.tank.repaint();
                }
            }
        });

        goldfish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Goldfish";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        plecostomus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Plecostomus";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        fishWithHat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Fish With Hat";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        cichlid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Cichlid";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        neonTetra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Neon Tetra";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        betta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Betta";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        snail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Snail";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        discus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Discus";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        platy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Platy";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });
        angelfish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = "Angelfish";
                price.setText("$10");
                if(FishTank.money < 10){
                    purchase.setEnabled(false);
                }else{
                    purchase.setEnabled(true);
                }
            }
        });

    }
}
