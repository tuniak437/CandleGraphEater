package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCSeries;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.net.MalformedURLException;
import java.time.Month;


class ChartHandler{

    private static final Logger log = LogManager.getLogger(ChartHandler.class.getName());

        static OHLCChart createChart(Integer year, Month month) {
            final OHLCChart chart = new OHLCChart(800, 600);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
            chart.getStyler().setDefaultSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Candle);

            
            DataHandler myDataHandler = new DataHandler();

            JsonHandler jsonHandler = null;
            try {
                jsonHandler = new JsonHandler();
            } catch (MalformedURLException e) {
                log.error(e.getMessage());
            }

            myDataHandler.addNewEntries(jsonHandler.parseJson());

            ChartData chartData = myDataHandler.getDataMap().get(year).get(month);
            chart.addSeries("Eur/Usd",
                chartData.getDay(),
                chartData.getOpen(),
                chartData.getHigh(),
                chartData.getLow(),
                chartData.getClose()
        )
                .setUpColor(Color.GREEN)
                .setDownColor(Color.RED)
        ;
        return chart;
        }
}
