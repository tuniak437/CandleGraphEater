package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCSeries;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

class ChartHandler {
    static OHLCChart createChart(HashMap<String, TreeMap<LocalDate, Double>> month) {
        final OHLCChart chart = new OHLCChart(800, 600);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);

        //creates chart that contains four ArrayLists of values from certain month
        chart.addSeries("Eur/Usd",
                new ArrayList<>(month.get("open").values()),
                new ArrayList<>(month.get("high").values()),
                new ArrayList<>(month.get("low").values()),
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
