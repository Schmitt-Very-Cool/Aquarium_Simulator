package FishModels;

import DESF.DiscreteEventSim;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FishTank {
    public static final int BG_NONE = 0;
    public static final int BG_LEAFY = 1;
    public static final int BG_SPINY = 2;
    public static final int BG_ROCKY = 3;

    public static final int CP_NONE = 0;
    public static final int CP_SKULL = 1;
    public static final int CP_COLOSSEUM = 2;
    public static final int CP_SHIPWRECK = 3;

    public static final int BB_NONE = 0;
    public static final int BB_FROG = 1;
    public static final int BB_CHEST = 2;


    public static int background = BG_NONE;
    public static int centerpiece = CP_NONE;
    public static int bubbler = BB_NONE;

    public static int hunger = 0;
    public static int dirtiness = 0;
    public static int money = 0;
    static int numFish = 0;
    public static ArrayList<Fish> fishes = new ArrayList<>();
    static TankWideEventModel twem = new TankWideEventModel();
    public static ArrayList<Decor> extraDecor = new ArrayList<>();

    public static void startupFishTank() throws IOException {
        DiscreteEventSim.addModel(twem, "", false);
    }

    public static void addFish(String s, String n) throws IOException {
        int t;
        if(s.equals("Goldfish")){
            t = 0;
        }else if(s.equals("Plecostomus")){
            t = 1;
        }else if(s.equals("Fish With Hat")){
            t = 2;
        }else if(s.equals("Cichlid")){
            t = 3;
        }else if(s.equals("Neon Tetra")){
            t = 4;
        }else if(s.equals("Betta")){
            t = 5;
        }else if(s.equals("Snail")){
            t = 6;
        }else if(s.equals("Discus")){
            t = 7;
        }else if(s.equals("Platy")){
            t = 8;
        }else{
            t = 9;
        }
        Fish f = new Fish(n, t, DiscreteEventSim.t);
        fishes.add(f);
        DiscreteEventSim.addModel(f, "TankWideEventModel", false);
        numFish++;
        twem.output = "fish " + f.typeName + " " + f.name;
        twem.nextInternal = DiscreteEventSim.t;
    }

    static void addFish(Fish f){
        fishes.add(f);
        numFish++;
        twem.output = "fish " + f.typeName;
        twem.nextInternal = DiscreteEventSim.t;
    }

    public static void dirty(){
        dirtiness++;
        if(dirtiness > 10){
            dirtiness = 10;
        }
        Main.gui.updateMetrics();
    }

    public static void hungryfy(){
        hunger++;
        Main.gui.updateMetrics();
    }

    public static void moneyfy(int x){
        money++;
        Main.gui.updateMetrics();
        Main.gui.tank.addCoin(x);
    }

    public static void feed(){
        if(hunger >= 3) {
            for (Fish f : fishes) {
                f.changeHappiness(1);
            }
        }
        hunger = 0;
        Main.gui.updateMetrics();
    }

    public static void clean(){
        if(dirtiness >= 3) {
            twem.output = "clean";
            twem.nextInternal = DiscreteEventSim.t;
        }
        dirtiness = 0;
        Main.gui.updateMetrics();
    }

    public static void addDecor(int r, int t){
        String o = "decor ";

        if (r == 0) {//background
            if (t == 1) {
                o += "leafy";
            } else if (t == 2) {
                o += "spiny";
            } else {
                o += "rocky";
            }
            o += " ";
            if (background == 0) {
                o += "none";
            } else if (background == 1) {
                o += "leafy";
            } else if (background == 2) {
                o += "spiny";
            } else {
                o += "rocky";
            }
            background = t;
        } else if (r == 1) {//centerpiece
            if (t == 1) {
                o += "skull";
            } else if (t == 2) {
                o += "colosseum";
            } else {
                o += "shipwreck";
            }
            o += " ";
            if (centerpiece == 0) {
                o += "none";
            } else if (centerpiece == 1) {
                o += "skull";
            } else if (centerpiece == 2) {
                o += "colosseum";
            } else {
                o += "shipwreck";
            }
            centerpiece = t;
        } else if (r == 2) {//bubbler
            if (t == 1) {
                o += "frog";
            } else {
                o += "chest";
            }
            o += " ";
            if (bubbler == 0) {
                o += "none";
            } else if (bubbler == 1) {
                o += "frog";
            } else {
                o += "chest";
            }
            bubbler = t;
        } else {//extra
            if (t == 1) {
                o += "scuba";
            } else {
                o += "disco";
            }
            o += " none";
            extraDecor.add(new Decor(t));
        }
        twem.output = o;
        twem.nextInternal = DiscreteEventSim.t;
    }
}
