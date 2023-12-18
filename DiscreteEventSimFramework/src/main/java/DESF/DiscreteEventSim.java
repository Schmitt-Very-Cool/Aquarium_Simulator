package DESF;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DiscreteEventSim {
    static ArrayList<Model> models;
    static PriorityQueue<DiscreteEvent> inputs;
    static ArrayList<IOLink> ioLinks;
    public static double t;

    public DiscreteEventSim(){
        models = new ArrayList<>();
        inputs = new PriorityQueue<>();
        ioLinks = new ArrayList<>();
    }

    public static void addModel(Model m, String input, boolean fileInput) throws IOException {
        models.add(m);
        if(fileInput){
            //add file inputs to inputs queue
            File f = new File(input);
            /*try {*/
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String i;
                while((i = br.readLine()) != null){
                    DiscreteEvent e = new DiscreteEvent();
                    e.model = m.id;
                    e.input = i.substring(i.indexOf(' ')+1);
                    int comma = i.indexOf(",");
                    if(comma == -1){
                        e.t = Double.parseDouble(i.split(" ")[0]);
                    }else{
                        e.t = Double.parseDouble(i.split(",")[0]);
                    }
                    inputs.add(e);
                    System.out.println("Added input " + e.input + " for model " + e.model + " set to time " + e.t);
                }
                br.close();
            /*}catch(IOException E){
                return;
            }*/
        }else{
            //link this model's input to the output of another
            IOLink l = new IOLink(m.id, input);
            ioLinks.add(l);
        }
    }

    public void run() throws InterruptedException {
        System.out.println("DEBUG: Listing all inputs in input queue");
        for(DiscreteEvent e : inputs){
            System.out.println(e.t + ": " + e.model + "--- " + e.input);
        }


        t = 0;
        DiscreteEvent e;
        Model m = null;

        while(!allDone()){
            double oldT = t;
            e = inputs.peek(); //here so the IDE doesn't yell at me
            if(inputs.peek() == null) {
                t = Double.POSITIVE_INFINITY;
            }else{
                t = inputs.peek().t;
            }

            boolean internal = false;
            boolean external = false;

            //check for internal events;
            double min = Double.POSITIVE_INFINITY;
            for(Model mod : models){
                if(min > mod.nextInternal){
                    m = mod;
                    min = mod.nextInternal;
                }
            }

            if(t <= min){
                external = true;
                e = inputs.poll();
            }if(min <= t){
                t = min;
                internal = true;
            }

            Thread.sleep(Math.max((int)((t - oldT)*1000),0));

            System.out.println("t = " + t);


            if(internal && external){
                //confluent
                getModel(e.model).confluentDelta(t, e.input);
                tryOutputLink(m, t);
            }else if(external){
                getModel(e.model).externalDelta(t, e.input);
            }else if(internal){
                m.internalDelta(t);
                tryOutputLink(m, t);
            }
        }
        System.out.println("Simulation End");
    }
    private void tryOutputLink(Model m, double t){
        for(IOLink l : ioLinks){
            if(l.Omodel.equals(m.id)){
                if(getModel(l.Imodel) == null){
                    continue;
                }
                DiscreteEvent d = new DiscreteEvent();
                d.model = l.Imodel;
                d.t = t;
                d.input = m.output;
                inputs.add(d);
            }
        }
    }
    private Model getModel(String id){
        for(Model mod : models){
            if(mod.id.equals(id)){
                return mod;
            }
        }
        return null;
    }

    private boolean allDone(){
        if(inputs.size() > 0){
            return false;
        }
        for(Model mod : models){
            if(mod.nextInternal < Double.POSITIVE_INFINITY){
                return false;
            }
        }
        return true;
    }
}
