package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCSeries;
import org.knowm.xchart.style.Styler;

import java.time.Month;
import java.util.*;


class ChartHandler{

    private static final Logger log = LogManager.getLogger(ChartHandler.class.getName());

        static OHLCChart createChart(Map<Integer, TreeMap<Month, ChartData>> dataMap) {
            final OHLCChart chart = new OHLCChart(800, 600);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
            chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);

            List<Date> days = new ArrayList<>();
            List<Double> open = new ArrayList<>();
            List<Double> high = new ArrayList<>();
            List<Double> low = new ArrayList<>();
            List<Double> close = new ArrayList<>();

            for (Integer year : dataMap.keySet()) {
                for (Month month : dataMap.get(year).keySet()) {
                    days.addAll(dataMap.get(year).get(month).getDay());
                    open.addAll(dataMap.get(year).get(month).getOpen());
                    high.addAll(dataMap.get(year).get(month).getHigh());
                    low.addAll(dataMap.get(year).get(month).getLow());
                    close.addAll(dataMap.get(year).get(month).getClose());
                }
            }
            chart.addSeries("Eur/Usd",
                    days,
                    open,
                    high,
                    low,
                    close
            );
            return chart;
        }

        static OHLCChart createChart(ChartData chartData) {
            final OHLCChart chart = new OHLCChart(800, 600);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
            chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);

            chart.addSeries("Eur/Usd",
                chartData.getDay(),
                chartData.getOpen(),
                chartData.getHigh(),
                chartData.getLow(),
                chartData.getClose()
        );
        return chart;
        }
}
