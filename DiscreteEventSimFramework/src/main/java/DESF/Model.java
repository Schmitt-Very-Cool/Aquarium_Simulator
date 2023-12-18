package DESF;

public abstract class Model<I,O>{
    public double nextInternal = Double.POSITIVE_INFINITY;
    public String output;
    public String id;

    public abstract O lambda();
    public abstract void internalDelta(double t);
    public abstract void externalDelta(double t, I input);
    public abstract void confluentDelta(double t, I input);
    public abstract void timeAdvance(double t);
}
