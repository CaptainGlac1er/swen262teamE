package FTPS;

/**
 * Created by Steven on 4/5/2016.
 */
public class WatchStock implements Visitable {
    private StockChild stockAbbreviation;
    private String triggertype;
    private double highTrigger, lowTrigger;
    //WatchListVisitor visitor;
    //cash accounts goo exampleof why to use visitor for future development

    public WatchStock(StockChild stockChild, double high, double low, String trigger){
        stockAbbreviation=stockChild;
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

    public StockChild getStockInfo(){
        return stockAbbreviation;
    }

    public void setTrigger(String Ctrigger){
        triggertype=Ctrigger;
    }
    public void setHighTrigger(double newHighTrigger){highTrigger=newHighTrigger;}
    public void setLowTrigger(double newLowTigger){lowTrigger=newLowTigger;}
    public String getTriggertype(){
        return triggertype;
    }

    @Override
    public WatchStock accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
