package GUI;

import FishModels.Decor;
import FishModels.Fish;
import FishModels.FishTank;
import FishModels.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class TankPanel extends JPanel {

    BufferedImage bg;
    BufferedImage tankFront;
    BufferedImage leafy;
    BufferedImage spiny;
    BufferedImage rocky;
    BufferedImage skull_f;
    BufferedImage skull_b;
    BufferedImage colosseum_f;
    BufferedImage colosseum_b;
    BufferedImage shipwreck_f;
    BufferedImage shipwreck_b;
    BufferedImage chest;
    BufferedImage frog;
    BufferedImage bubbles;
    BufferedImage scuba;
    BufferedImage disco;
    BufferedImage goldfish;
    BufferedImage plecostomus;
    BufferedImage fishwithhat;
    BufferedImage cichlid;
    BufferedImage neon;
    BufferedImage betta;
    BufferedImage snail;
    BufferedImage discus;
    BufferedImage platy;
    BufferedImage angelfish;
    BufferedImage money;
    volatile ArrayList<Money> monies = new ArrayList<>();

    public TankPanel() throws IOException {
        loadImages();
        setPreferredSize(new Dimension(800,450));
        setBackground(Color.CYAN);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    fishClick(e);
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    FishTank.moneyfy(10);
                }
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
    }

    private void loadImages() throws IOException {
        bg = ImageIO.read(new File("assets/background.png"));
        angelfish = ImageIO.read(new File("assets/angelfish.png"));
        betta = ImageIO.read(new File("assets/betta.png"));
        bubbles = ImageIO.read(new File("assets/bubbles.png"));
        chest = ImageIO.read(new File("assets/chest.png"));
        cichlid = ImageIO.read(new File("assets/cichlid.png"));
        colosseum_b = ImageIO.read(new File("assets/colosseum_back.png"));
        colosseum_f = ImageIO.read(new File("assets/colosseum_front.png"));
        disco = ImageIO.read(new File("assets/disco.png"));
        discus = ImageIO.read(new File("assets/discus.png"));
        fishwithhat = ImageIO.read(new File("assets/fishwithhat.png"));
        frog = ImageIO.read(new File("assets/frog.png"));
        goldfish = ImageIO.read(new File("assets/goldfish.png"));
        leafy = ImageIO.read(new File("assets/leafy.png"));
        neon = ImageIO.read(new File("assets/neon.png"));
        platy = ImageIO.read(new File("assets/platy.png"));
        plecostomus = ImageIO.read(new File("assets/plecostomus.png"));
        rocky = ImageIO.read(new File("assets/rocky.png"));
        scuba = ImageIO.read(new File("assets/scuba.png"));
        shipwreck_b = ImageIO.read(new File("assets/shipwreck_back.png"));
        shipwreck_f = ImageIO.read(new File("assets/shipwreck_front.png"));
        skull_b = ImageIO.read(new File("assets/skull_back.png"));
        skull_f = ImageIO.read(new File("assets/skull_front.png"));
        snail = ImageIO.read(new File("assets/snail.png"));
        spiny = ImageIO.read(new File("assets/spiny.png"));
        tankFront = ImageIO.read(new File("assets/tank_front.png"));
        money = ImageIO.read(new File("assets/fishmoney.png"));
    }

    private void fishClick(MouseEvent e) {
        for(Fish f : FishTank.fishes){
            if(e.getX() >= f.loc.x-f.w/2 && e.getX() <= f.loc.x+f.w/2 && e.getY() >= f.loc.y-f.h/2 && e.getY() <= f.loc.y+f.h/2){
                Main.gui.stats.setDetails(f);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //paint background
        g.drawImage(bg,0,0,getWidth(),getHeight(),null);
        if(FishTank.background == FishTank.BG_LEAFY){
            g.drawImage(leafy,0,0,getWidth(),getHeight(),null);
        }else if(FishTank.background == FishTank.BG_SPINY){
            g.drawImage(spiny,0,0,getWidth(),getHeight(),null);
        }else if(FishTank.background == FishTank.BG_ROCKY){
            g.drawImage(rocky,0,0,getWidth(),getHeight(),null);
        }

        //paint middle
        if(FishTank.centerpiece == FishTank.CP_SKULL){
            g.drawImage(skull_b,0,0,getWidth(),getHeight(),null);
        }else if(FishTank.centerpiece == FishTank.CP_COLOSSEUM){
            g.drawImage(colosseum_b,0,0,getWidth(),getHeight(),null);
        }else if(FishTank.centerpiece == FishTank.CP_SHIPWRECK){
            g.drawImage(shipwreck_b,0,0,getWidth(),getHeight(),null);
        }

        if(FishTank.bubbler == FishTank.BB_FROG){
            g.drawImage(frog,0,0,getWidth(),getHeight(),null);
        }else if(FishTank.bubbler == FishTank.BB_CHEST){
            g.drawImage(chest,0,0,getWidth(),getHeight(),null);
        }
        if(FishTank.bubbler != FishTank.BB_NONE){
            g.drawImage(bubbles,0,0,getWidth(),getHeight(),null);
        }

        if(FishTank.centerpiece == FishTank.CP_SKULL){
            g.drawImage(skull_f,0,0,getWidth(),getHeight(),null);
        }else if(FishTank.centerpiece == FishTank.CP_COLOSSEUM){
            g.drawImage(colosseum_f,0,0,getWidth(),getHeight(),null);
        }else if(FishTank.centerpiece == FishTank.CP_SHIPWRECK){
            g.drawImage(shipwreck_f,0,0,getWidth(),getHeight(),null);
        }

        //draw front of tank
        g.drawImage(tankFront,0,0,getWidth(),getHeight(),null);

        //paint extra decor
        for(Decor d : FishTank.extraDecor){
            if(d.type == Decor.DC_SCUBA){
                g.drawImage(scuba,d.loc.x - d.w/2,d.loc.y - d.h/2,d.w,d.h,null);
            }else {
                g.drawImage(disco, d.loc.x - d.w/2, d.loc.y - d.h/2, d.w, d.h, null);
            }
        }

        //draw fishes
        for(Fish f : FishTank.fishes){
            if(f.type == 0){
                g.drawImage(goldfish, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 1){
                g.drawImage(plecostomus, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 2){
                g.drawImage(fishwithhat, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 3){
                g.drawImage(cichlid, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 4){
                g.drawImage(neon, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 5){
                g.drawImage(betta, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 6){
                g.drawImage(snail, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 7){
                g.drawImage(discus, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 8){
                g.drawImage(platy, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }else if(f.type == 9){
                g.drawImage(angelfish, f.loc.x - f.w/2, f.loc.y - f.h/2, null);
            }
        }

        //draw coins
        boolean drawingMoney = true;
        while(drawingMoney) {
            try {
                for (Money m : monies) {
                    g.drawImage(money, m.x - 25, m.y - 25, null);
                }
                drawingMoney = false;
            }catch (ConcurrentModificationException e){
                continue;
            }
        }

    }

    public void addCoin(int x){
        Money m = new Money();
        m.x = x;
        monies.add(m);
        MoneyMotionHandler mmh = new MoneyMotionHandler(m);
        mmh.execute();
    }

    public Point newLoc(int w, int h){
        return new Point((int)(Math.random()*(getWidth()-2*w-40))+w+20,(int)(Math.random()*(getHeight()-2*h-60)+h+20));
    }
}
