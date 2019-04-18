package cz.tuniak;

import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.awt.*;

class UIHandler {
    static void createFrame(OHLCChart chart) {

        //creating and setting up window
        JFrame frame = new JFrame("Index Graph");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //adding chart into window
        JPanel chartPanel = new XChartPanel<>(chart);
        frame.add(chartPanel, BorderLayout.CENTER);

        //labeling window
//        JLabel label = new JLabel("Name of currency type will be here", SwingConstants.CENTER);
//        frame.add(label, BorderLayout.SOUTH);

        //displaying the window
        frame.pack();
        frame.setVisible(true);
    }
}
