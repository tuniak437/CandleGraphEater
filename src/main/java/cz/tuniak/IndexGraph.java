package cz.tuniak;

import org.knowm.xchart.XYChart;

public class IndexGraph {

    public static void main(String[] args) {
        XYChart chart = ChartHandler.createChart();
        HandlerUI.createFrame(chart);
        String number = JsonHandler.parseJson();
        double value = Double.parseDouble(number);
        System.out.println(value);
    }
}