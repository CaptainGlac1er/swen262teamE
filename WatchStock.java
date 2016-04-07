package FTPS;

/**
 * Created by Steven on 4/5/2016.
 */
public class WatchStock implements Visitable {
    private String stockAbbreviation, triggertype;
    private double highTrigger, lowTrigger;
    private Visitor visitor;
    //cash accounts goo exampleof why to use visitor for future development

    public WatchStock(String abrv, double high, double low, String trigger){
        stockAbbreviation=abrv;
        highTrigger=high;
        lowTrigger=low;
        triggertype=trigger;

    }


    public double getHighTrigger(){
        return highTrigger;
    }

    public double getLowTrigger(){
        return lowTrigger;
    }

    public String getAbbreviation(){
        return stockAbbreviation;
    }

    public void setTrigger(String Ctrigger){
        triggertype=Ctrigger;
    }
    public String getTriggertype(){
        return triggertype;
    }

    @Override
    public WatchStock accept(Visitor vistor) {
        return visitor.visit(this);
    }
}
