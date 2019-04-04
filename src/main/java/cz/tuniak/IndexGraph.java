package cz.tuniak;

import org.knowm.xchart.OHLCChart;

public class IndexGraph {

    public static void main(String[] args) {
        OHLCChart chart = ChartHandler.createChart();
        UIHandler.createFrame(chart);
//        String number = JsonHandler.getOpenDataValues(JsonHandler.parseJson(),"2019-03-");
//        double value = Double.parseDouble(number);
//        System.out.println(value);
//        JsonHandler.createMonthString().forEach(System.out::println);
//        JsonHandler.parseJson();
    }
}