//package cz.tuniak;
//
//import org.knowm.xchart.OHLCChart;
//import org.knowm.xchart.OHLCSeries;
//import org.knowm.xchart.style.Styler;
//
//import java.awt.*;
//
//class ChartHandler {
//   static OHLCChart createChart() {
//       final OHLCChart chart = new OHLCChart(800, 600);
//       chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
//       chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);
//
//       chart.addSeries("Eur/Usd",
//               DataHandler.getOpenData(),
//               DataHandler.getHighData(),
//               DataHandler.getLowData(),
//               DataHandler.getCloseData()
//               )
//               .setUpColor(Color.GREEN)
//               .setDownColor(Color.RED)
//       ;
////        .title("Area Chart")
////                 .xAxisTitle("X")
////                 .yAxisTitle("Y")
////                 .build();
//       return chart;
//   }
//}
