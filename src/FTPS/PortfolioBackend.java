package FTPS;

/**
 * Created by George Walter Colrove IV on 3/23/2016.
 */
public class PortfolioBackend extends PageBackend implements Updatable {
    PageUpdater pageUpdater;
    private double portfolioWorth = 0;
    AssetsBackend assetsBackend;
    AccountBackend accountBackend;
    WatchListBackend watchListBackend;
    TransactionsBackend transactionsBackend;
    PortfolioGUI portfolioGUI;
    Portfolio portfolio;
    public PortfolioBackend(PageUpdater pageUpdater, User user){
        portfolio = new Portfolio(user, this);
        assetsBackend = new AssetsBackend(new PageUpdater(), portfolio);
        accountBackend = new AccountBackend(new PageUpdater(), portfolio);
        watchListBackend = new WatchListBackend(new PageUpdater());
        transactionsBackend = new TransactionsBackend(new PageUpdater(), portfolio);
        portfolioGUI = new PortfolioGUI(this, new PortfolioUpdater(), (AccountGUI)accountBackend.getPage(), (AssetsGUI)assetsBackend.getPage(), (WatchListGUI)watchListBackend.getPage(), (TransactionsGUI) transactionsBackend.getPage());
        setGUI(portfolioGUI);
        this.pageUpdater = pageUpdater;
        pageUpdater.setCurrentPageAndBackend(getGUI(), this);
    }
    public Portfolio getPortfolio(){
        return portfolio;
    }
    @Override
    public void update() {
        assetsBackend.update();
        accountBackend.update();
        watchListBackend.update();
        transactionsBackend.update();
        portfolioGUI.update();
    }
    public void update(double currentPortfolioWorth){
        portfolioWorth = currentPortfolioWorth;
        update();
    }
    public void savePortfolio(){
        portfolio.exportPortfolio(false);
    }
}
