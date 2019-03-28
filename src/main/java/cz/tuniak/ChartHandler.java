package cz.tuniak;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

class ChartHandler {
   static XYChart createChart() {
        final XYChart chart = new XYChart(800, 600);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("a", new double[]{0, 1, 2, 3, 5}, new double[]{-3, 5, 9, 6, 5});
        chart.addSeries("b", new double[]{0, 1, 2, 3, 4}, new double[]{-1, 6, 4, 0, 4});
//        .title("Area Chart")
//                 .xAxisTitle("X")
//                 .yAxisTitle("Y")
//                 .build();
        return chart;
    }
}
