package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCSeries;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ChartHandler {

  private static final Logger log = LogManager.getLogger(ChartHandler.class.getName());

  // create chart method returns chart with 5 arguments
  // getMonthChart gets chart data (1 month) appends lists
  // on object, call create chart and return it
  // get whole chart gets all months and creates chart from it
  private List<Date> day;
  private List<Double> open;
  private List<Double> high;
  private List<Double> low;
  private List<Double> close;

  ChartHandler() {
    this.day = new ArrayList<>();
    this.open = new ArrayList<>();
    this.high = new ArrayList<>();
    this.low = new ArrayList<>();
    this.close = new ArrayList<>();
  }

  OHLCChart getMonthChart(ChartData chartData) {
    appendData(chartData);
    return createChartXOXO(
        chartData.getDay(),
        chartData.getOpen(),
        chartData.getHigh(),
        chartData.getLow(),
        chartData.getClose());
  }

  private void appendData(ChartData chartData) {
    day.addAll(chartData.getDay());
    open.addAll(chartData.getOpen());
    high.addAll(chartData.getHigh());
    low.addAll(chartData.getLow());
    close.addAll(chartData.getClose());
  }

  OHLCChart getWholeChart() {
    return createChartXOXO(day, open, high, low, close);
  }

  private OHLCChart createChartXOXO(
      List<Date> day,
      List<Double> open,
      List<Double> high,
      List<Double> low,
      List<Double> close) {
    final OHLCChart chart = new OHLCChart(800, 600);
    chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
    chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);

    chart.addSeries("Eur/Usd", day, open, high, low, close);
    return chart;
  }
}
