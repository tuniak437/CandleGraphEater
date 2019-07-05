package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCSeries;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.io.IOException;


class ChartHandler{

    private static final Logger log = LogManager.getLogger(ChartHandler.class.getName());

        static OHLCChart createChart() {
            final OHLCChart chart = new OHLCChart(800, 600);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
            chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);

            JSONObject jsonDataObject = null;
            try {
                jsonDataObject = JsonHandler.parseJson();
            } catch (IOException e) {
                UIHandler.showMessage(UIHandler.createFrame(), "Something went wrong.");
                log.error("ChartHandler.catch", e);
            }
            ChartData myChartData = new ChartData(jsonDataObject);
            chart.addSeries("Eur/Usd",
                myChartData.getDays(),
                myChartData.getOpenValues(),
                myChartData.getHighValues(),
                myChartData.getLowValues(),
                myChartData.getCloseValues()
        )
                .setUpColor(Color.GREEN)
                .setDownColor(Color.RED)
        ;
        return chart;
        }
}
