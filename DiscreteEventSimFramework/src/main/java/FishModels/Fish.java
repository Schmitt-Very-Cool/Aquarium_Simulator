package FishModels;

import DESF.Model;
import GUI.FishMover;

import java.awt.*;

public class Fish extends Model<String, String>{
    public int happiness = 1;
    double nextMoney = Double.POSITIVE_INFINITY;
    double nextMovement = 1.0;
    public Point loc;
    boolean nextIsMoney = false;
    public int type = 0;
    boolean feelsYucky = false;
    public String name;
    public String typeName;
    public String favDecor;
    public boolean schooling;
    public int yuckyThreshold;
    public int w;
    public int h;

    public Fish(String s, int t){
        this(s, t, 0);
    }
    public Fish(String s, int typ, double t){
        id = s;
        name = s;
        type = typ;
        nextMoney = t + (Math.random()*10)/happiness;
        nextMovement = t + (Math.random()*5 + 5);
        loc = Main.newLoc(w, h);
        timeAdvance(t);

        switch(type){
            case 0:
                //goldfish
                typeName = "Goldfish";
                favDecor = "leafy";
                schooling = false;
                yuckyThreshold = 5;
                w = 100;
                h = 50;
                break;
            case 1:
                //plecostomus
                typeName = "Plecostomus";
                favDecor = "rocky";
                schooling = false;
                yuckyThreshold = 8;
                w = 150;
                h = 50;
                break;
            case 2:
                //fish with hat
                typeName = "Fish With Hat";
                favDecor = "shipwreck";
                schooling = false;
                yuckyThreshold = 4;
                w = 100;
                h = 50;
                break;
            case 3:
                //cichlid
                typeName = "Cichlid";
                favDecor = "spiny";
                schooling = true;
                yuckyThreshold = 6;
                w = 200;
                h = 100;
                break;
            case 4:
                //neon tetra
                typeName = "Neon Tetra";
                favDecor = "disco";
                schooling = true;
                yuckyThreshold = 3;
                w = 50;
                h = 25;
                break;
            case 5:
                //betta
                typeName = "Betta";
                favDecor = "skull";
                schooling = false;
                yuckyThreshold = 3;
                w = 150;
                h = 100;
                break;
            case 6:
                //snail
                typeName = "Snail";
                favDecor = "colosseum";
                schooling = true;
                yuckyThreshold = 10;
                w = 50;
                h = 50;
                break;
            case 7:
                //discus
                typeName = "Discus";
                favDecor = "scuba";
                schooling = true;
                yuckyThreshold = 4;
                w = 150;
                h = 150;
                break;
            case 8:
                //platy
                typeName = "Platy";
                favDecor = "frog";
                schooling = true;
                yuckyThreshold = 5;
                w = 100;
                h = 50;
                break;
            case 9:
                //Angelfish
                typeName = "Angelfish";
                favDecor = "chest";
                schooling = false;
                yuckyThreshold = 4;
                w = 100;
                h = 100;
        }
    }
    @Override
    public String lambda() {
        return null;
    }

    @Override
    public void internalDelta(double t) {
        if(nextIsMoney){
            System.out.println(name + " finds money.");
            FishTank.moneyfy(loc.x);
            nextMoney = t + (Math.random()*20)/Math.sqrt(happiness);
        }else{
            System.out.println(name + " moves.");
            Point newLoc = Main.newLoc(w, h);
            double oldt = nextMovement;
            nextMovement = t + (Math.random()*5 + 5);
            FishMover fm = new FishMover(this, loc.x, loc.y, newLoc.x, newLoc.y, (int)((nextMovement-oldt)*1000));
            fm.execute();
        }
        timeAdvance(t);
    }

    @Override
    public void externalDelta(double t, String input) {
        System.out.println(name + " receives input: " + input);
        if(input.equals("clean")){
            changeHappiness(1);
            feelsYucky = FishTank.dirtiness > yuckyThreshold;
            return;
        }if(input.equals("dirty")){
            feelsYucky = FishTank.dirtiness > yuckyThreshold;
            if(feelsYucky) {
                changeHappiness(-1);
            }
            return;
        }if(input.equals("hungry")) {
            if(FishTank.hunger >= 7){
                changeHappiness(-1);
            }
        }if(input.split(" ")[0].equals("decor")){
            if(input.split(" ")[1].equals(favDecor) && !input.split(" ")[2].equals(favDecor)){
                changeHappiness(1);
            }else if(input.split(" ")[2].equals(favDecor) && !input.split(" ")[1].equals(favDecor)){
                changeHappiness(-1);
            }
        }if(input.split(" ")[0].equals("fish")){
            if(FishTank.numFish > 10){
                changeHappiness(-1);
            }
            if(!input.split(" ")[2].equals(name)) {
                if (schooling && input.split(" ")[1].equals(typeName)) {
                    changeHappiness(1);
                }
                if (!schooling && input.split(" ")[1].equals(typeName)) {
                    changeHappiness(-1);
                }
            }
        }
    }

    @Override
    public void confluentDelta(double t, String input) {
        internalDelta(t);
        externalDelta(t, input);
    }

    @Override
    public void timeAdvance(double t) {
        if(nextMoney < nextMovement){
            nextIsMoney = true;
        }else{
            nextIsMoney = false;
        }
        if(nextIsMoney){
            nextInternal = nextMoney;
        }else{
            nextInternal = nextMovement;
        }
    }

    public void changeHappiness(int change){
        System.out.println(name + "changes happiness by " + change);
        happiness += change;
        if(happiness > 10){
            happiness = 10;
        }
        if(happiness < 0){
            happiness = 0;
        }
        Main.gui.updateHappiness(name, happiness);
    }
}
