package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.internal.chartpart.Chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.Map;
import java.util.TreeMap;

class UIHandler {
  static JFrame createFrame() {
    // creating and setting up frame
    JFrame frame = new JFrame("Candle Graph");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//    JLabel loadingLabel =
//        showMessage(frame, "Loading..");
    //    frame.add(loadingLabel, BorderLayout.NORTH);

    frame.setSize(1000, 600);
    frame.setVisible(true);
    //    frame.remove(loadingLabel);

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

  static void showMessage(JFrame jFrame, String message) {
    final JLabel label = new JLabel();
    int timerDelay = 1000;
    new Timer(
        timerDelay,
        new ActionListener() {
          int timeLeft = 2;

          @Override
          public void actionPerformed(ActionEvent e) {
            if (timeLeft > 0) {
              label.setText(message);
              timeLeft--;
            } else {
              ((Timer) e.getSource()).stop();
              SwingUtilities.getWindowAncestor(label).setVisible(false);
            }
          }
        }) {
      {
        setInitialDelay(0);
      }
    }.start();

    JOptionPane.showMessageDialog(jFrame, label);
  }
}
