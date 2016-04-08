package FTPS;

/**
 * Created by Steven on 4/5/2016.
 */
public class WatchListVisitor implements Visitor {
    private WatchListStock watchListStock;
    private SearchStock searchStock;

    @Override
    public WatchStock visit(WatchStock watchStock) {
        //System.out.println(searchStock.getStock(watchStock.getAbbreviation()).getTotWorth());
        if (watchStock.getHighTrigger() < watchStock.getStockInfo().getTotWorth()) {
            watchStock.setTrigger("high");
            return watchStock;
        }
        if (watchStock.getLowTrigger() < watchStock.getStockInfo().getTotWorth()){
            watchStock.setTrigger("low");
            return watchStock;
        }
        else {
            watchStock.setTrigger("none");
            return watchStock;
        }
    }

}
