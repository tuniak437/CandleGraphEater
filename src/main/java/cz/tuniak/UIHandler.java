package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.internal.chartpart.Chart;

import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.util.Map;
import java.util.TreeMap;

class UIHandler {
  static JFrame createFrame() {
    // creating and setting up frame
    JFrame frame = new JFrame("Candle Graph");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // labeling window
    JLabel label = new JLabel("My fancy graph", SwingConstants.CENTER);
    frame.add(label, BorderLayout.NORTH);

    frame.setSize(1200, 800);
    frame.setVisible(true);

    return frame;
  }

  static void addDataToFrame(JFrame frame, Map<Integer, TreeMap<Month, ChartData>> dataMap) {
    frame.add(buildTabs(dataMap), BorderLayout.CENTER);

    // displaying the window
    frame.pack();
    frame.setVisible(true);
  }

  private static JTabbedPane buildTabs(Map<Integer, TreeMap<Month, ChartData>> dataMap) {

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    ChartHandler myChartHandler = new ChartHandler();
    // getting dataMap to create candle chart with every month
    // looping through every year in dataMap
    for (Integer year : dataMap.keySet()) {
      // looping through every month in a year
      for (Month month : dataMap.get(year).keySet()) {
        // getting ChartData of exact month and year to create candle chart
        OHLCChart chart = myChartHandler.getMonthChart(dataMap.get(year).get(month));
        tabbedPane.add(month.toString() + " " + year, new XChartPanel<Chart>(chart));
      }
    }

    Component wholeChart = new XChartPanel<>(myChartHandler.getWholeChart());
    wholeChart.setName("Whole Chart");
    tabbedPane.add(wholeChart, 0);

    return tabbedPane;
  }

  static void showMessage(JFrame frame, String message) {
    JOptionPane.showMessageDialog(frame, message);
  }

  // needs work
  static JFrame loadingSign() {
    JFrame loading = new JFrame();
    loading.setLayout(new BorderLayout());
    loading.add(new TextArea("Loading..."), BorderLayout.CENTER);
    loading.setSize(300, 300);
    loading.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    loading.setVisible(true);

    return loading;
  }
}
