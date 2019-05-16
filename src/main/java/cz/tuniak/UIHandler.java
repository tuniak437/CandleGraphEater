package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.TreeMap;

class UIHandler {
    static JFrame createFrame(HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> data) {

        //creating and setting up window
        JFrame frame = new JFrame("Index Graph");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //labeling window
        JLabel label = new JLabel("My super fancy graph", SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);

        frame.add(buildMonthTabs(data), BorderLayout.CENTER);

        frame.setPreferredSize(new Dimension(1200, 800));

        //displaying the window
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    static JTabbedPane buildMonthTabs(HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> data) {

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        data.forEach((key, value) -> {
                    OHLCChart chart = ChartHandler.createChart(value);
//                    String month = value.get("open").firstKey().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

                    tabbedPane.add(Month.of(key).toString(), new XChartPanel(chart));
        });

        return tabbedPane;
    }
    static JPanel createChart(JFrame frame, OHLCChart chart) {

        //adding chart into window
        JPanel chartPanel = new XChartPanel<>(chart);
        frame.add(chartPanel, BorderLayout.CENTER);

        return chartPanel;
    }
}
