package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
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
                    tabbedPane.add(Month.getNameOfMonth(key), new XChartPanel(chart));
        });

        return tabbedPane;
    }
    static JPanel createChart(JFrame frame, OHLCChart chart) {

        //adding chart into window
        JPanel chartPanel = new XChartPanel<>(chart);
        frame.add(chartPanel, BorderLayout.CENTER);

        return chartPanel;
    }

    private enum Month {
        JANUARY(1, "JANUARY"),
        FEBRUARY(2, "FEBRUARY"),
        MARCH(3, "MARCH"),
        APRIL(4, "APRIL"),
        MAY(5, "MAY"),
        JUNE(6, "JUNE"),
        JULY(7, "JULY"),
        AUGUST(8, "AUGUST"),
        SEPTEMBER(9, "SEPTEMBER"),
        OCTOBER(10, "OCTOBER"),
        NOVEMBER(11, "NOVEMBER"),
        DECEMBER(12, "DECEMBER");

        private final int numberOfMonth;
        private final String nameOfMonth;

        Month(int numberOfMonth, String nameOfMonth) {
            this.numberOfMonth = numberOfMonth;
            this.nameOfMonth = nameOfMonth;
        }

        public static String getNameOfMonth(int numberOfMonth) {
            for (Month month: Month.values()) {

                if (month.numberOfMonth == numberOfMonth) {
                    return month.nameOfMonth;
                }
            }
            throw new IllegalArgumentException("Month with number " + numberOfMonth + " does not exist.");
        }
    }
}
