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
   synchronized static JFrame createFrame() {
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

    synchronized static void addDataToFrame(JFrame frame) {
        //start frame in new thread, downloading data in different thread
        OHLCChart chart = ChartHandler.createChart();
        XChartPanel cp = new XChartPanel(ChartHandler.createChart());
        frame.add(cp, BorderLayout.CENTER);

        //displaying the window
        frame.pack();
        frame.setVisible(true);
    }



    // needs adjusting for new ChartData class
    private static JTabbedPane buildMonthTabs(HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> data) {

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        data.forEach((key, value) -> {
                    OHLCChart chart = ChartHandler.createChart();
//                    String month = value.get("open").firstKey().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

                    tabbedPane.add(Month.of(key).toString(), new XChartPanel(chart));
        });

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
