package DESF;

public class DiscreteEvent implements Comparable<DiscreteEvent>{
    public String model;
    public String input;
    public double t;

    @Override
    public int compareTo(DiscreteEvent e){
        if(t == e.t){
            return model.compareTo(model);
        }
        return Double.compare(t,e.t);
    }
}
