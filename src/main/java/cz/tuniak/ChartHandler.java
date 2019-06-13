package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCSeries;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

class ChartHandler {
    //String = (open, high, low, close)
    //LocalDate = full date in format "yyyy-MM-dd" || Double = values
    static OHLCChart createChart(HashMap<String, TreeMap<LocalDate, Double>> month) {
        final OHLCChart chart = new OHLCChart(800, 600);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);
        //creates chart that contains four ArrayLists of values from certain month
//        YearMonth yearMonthObject = YearMonth.of();
//        int daysInMonth = yearMonthObject.lengthOfMonth();
        ArrayList xData = new ArrayList();
        month.get("open").forEach((keys, Double) -> {
            //days counter
//            YearMonth yearMonthObject = YearMonth.of(keys.getYear(), keys.getMonth());
//            int daysInMonth = yearMonthObject.lengthOfMonth();
//            List<Integer> days = IntStream.rangeClosed(1, YearMonth.of(keys.getYear(), keys.));
            Date days = Date.from(keys.atStartOfDay(ZoneId.systemDefault()).toInstant());
                xData.add(days);
        });

        chart.addSeries("Eur/Usd",
                //ChartData.getDays()
                xData,
                //ChartData.getOpenValues()
                new ArrayList<>(month.get("open").values()),
                //ChartData.getHighValues()
                new ArrayList<>(month.get("high").values()),
                //ChartData.getLowValues()
                new ArrayList<>(month.get("low").values()),
                //ChartData.getCloseValues()
                new ArrayList<>(month.get("close").values())
        )
                .setUpColor(Color.GREEN)
                .setDownColor(Color.RED)
        ;
//        .title("Area Chart")
//                 .xAxisTitle("X")
//                 .yAxisTitle("Y")
//                 .build();
        return chart;
    }
}
