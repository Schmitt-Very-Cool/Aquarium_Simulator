package FishModels;

import DESF.Model;

public class TankWideEventModel extends Model {
    double nextHunger = 0;
    double nextDirty = 0;

    public TankWideEventModel(){
        id = "TankWideEventModel";
        nextInternal = 0;
    }
    @Override
    public Object lambda() {
        return null;
    }

    @Override
    public void internalDelta(double t) {
        System.out.println("Global Tank Event");
        //called only to pass output onto all the fishes.
        if(nextDirty <= t){
            FishTank.dirty();
            output = "dirty";
        }else if(nextHunger <= t){
            FishTank.hungryfy();
            output = "hungry";
        }
        timeAdvance(t);
    }

    @Override
    public void externalDelta(double t, Object input) {
        //never called
    }

    @Override
    public void confluentDelta(double t, Object input) {
        //never called
    }

    @Override
    public void timeAdvance(double t) {
        if(t >= nextHunger){
            nextHunger = t + (Math.random()*50.0)/Math.sqrt(FishTank.numFish);
        }
        if(t >= nextDirty){
            nextDirty = t + (Math.random()*50.0)/Math.sqrt(FishTank.numFish);
        }
        nextInternal = Math.min(nextHunger, nextDirty);
    }

    public void adjustTime(){
        nextHunger -= Math.random()*50.0/(FishTank.numFish-1) - Math.random()*50.0/FishTank.numFish;
    }
}
