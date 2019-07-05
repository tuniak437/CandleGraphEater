package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.internal.chartpart.Chart;

import javax.swing.*;
import java.awt.*;
import java.time.Month;

class UIHandler {
   static JFrame createFrame() {
        //creating and setting up frame
        JFrame frame = new JFrame("Index Graph");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //labeling window
        JLabel label = new JLabel("My fancy graph", SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);

        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setVisible(true);

        return frame;
    }

    static void addDataToFrame(JFrame frame) {
        XChartPanel cp = new XChartPanel<Chart>(ChartHandler.createChart());
        frame.add(cp, BorderLayout.CENTER);

        //displaying the window
        frame.pack();
        frame.setVisible(true);
    }

    // needs adjusting for new ChartData class
    private static JTabbedPane buildMonthTabs(ChartData data) {

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        for(Month month : data.getMonths()) {
            OHLCChart chart = ChartHandler.createChart();
            tabbedPane.add(month.toString(), new XChartPanel<Chart>(chart));
        }
//        data.forEach((key, value) -> {
//                    OHLCChart chart = ChartHandler.createChart();
////                    String month = value.get("open").firstKey().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
//
//                    tabbedPane.add(Month.of(key).toString(), new XChartPanel<Chart>(chart));
//        });
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
