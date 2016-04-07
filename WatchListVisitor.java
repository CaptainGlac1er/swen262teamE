package FTPS;

/**
 * Created by Steven on 4/5/2016.
 */
public class WatchListVisitor implements Visitor {
    private WatchListStock watchListStock;
    private SearchStock searchStock;

    @Override
    public WatchStock visit(WatchStock watchStock) {
        if (watchStock.getHighTrigger() < searchStock.getStock(watchStock.getAbbreviation()).getTotWorth()) {
            watchStock.setTrigger("high");
            return watchStock;
        }
        if (watchStock.getLowTrigger() < searchStock.getStock(watchStock.getAbbreviation()).getTotWorth()){
            watchStock.setTrigger("low");
            return watchStock;
        }
        else {
            watchStock.setTrigger("none");
            return watchStock;
        }
    }
}
