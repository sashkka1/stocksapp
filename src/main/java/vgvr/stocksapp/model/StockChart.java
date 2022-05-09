package vgvr.stocksapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Data
@AllArgsConstructor
public class StockChart {
    private BigDecimal openPrice;
    private BigDecimal closePrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private Calendar date;

    public StockChart() {
    }

    public List<ArrayList> getStockCharts(String companyTicker) throws IOException {
        System.out.println("внутри arraylist 1"); //////////////////////////////////////////////////////////////////////////////
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        System.out.println("внутри arraylist 2"); //////////////////////////////////////////////////////////////////////////////
        from.add(Calendar.YEAR, -1);
        List<ArrayList> charts = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        Date date = new Date();
        System.out.println("внутри arraylist 3"); //////////////////////////////////////////////////////////////////////////////
        Stock stock = YahooFinance.get(companyTicker, from, to);
        System.out.println();
        int i = 1;
        System.out.println("внутри arraylist 4"); //////////////////////////////////////////////////////////////////////////////
        for(HistoricalQuote quote:stock.getHistory()){
            System.out.println("внутри arraylist 5"); //////////////////////////////////////////////////////////////////////////////
            ArrayList<BigDecimal> arr = new ArrayList<>();
            calendar = quote.getDate();
            date = calendar.getTime();
            System.out.println(date.getDay() + date.getMonth());
            arr.add(BigDecimal.valueOf(date.getDay()));
            arr.add(quote.getOpen());
            arr.add(quote.getClose());
            arr.add(quote.getHigh());
            arr.add(quote.getLow());
            charts.add(arr);
            i++;
        }
        return charts;
    }
    public static Stock get(String symbol, Calendar from, Calendar to, Interval interval) throws IOException {

        System.out.println("v, chf,jnftn");
        //Calendar from = Calendar.getInstance();
        //Calendar to = Calendar.getInstance();
        from.add(Calendar.YEAR, -5); // from 5 years ago

        Stock tesla = YahooFinance.get("TSLA", false);
        System.out.println(tesla.getHistory());
        Stock stock = YahooFinance.get(symbol,from, to , Interval.DAILY);
        System.out.println("v, chf,jnftn");
        return stock;
    };

    public List<StockChart> getStockData(String companyTicker) throws IOException {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.MONTH, -1);
        List<StockChart> arr = new ArrayList<>();
        Stock stock = YahooFinance.get(companyTicker, from, to, Interval.DAILY);
        int i = 1;

        for(HistoricalQuote quote:stock.getHistory()){
            arr.add(new StockChart(quote.getOpen(),quote.getClose(),quote.getHigh(),quote.getLow(), quote.getDate()));
        }
        return arr;
    }
}
