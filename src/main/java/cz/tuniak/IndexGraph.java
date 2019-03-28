package cz. tuniak;

import org.json.JSONObject;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IndexGraph {

    public static void main(String[] args) throws Exception {

        String number = parseJson();
        double value = Double.parseDouble(number);
        createFrame();
        System.out.println(value);
    }

    private static XYChart createChart() {
        final XYChart chart = new XYChart(800, 600);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("a", new double[]{0, 1, 2, 3, 5}, new double[]{-3, 5, 9, 6, 5});
        chart.addSeries("b", new double[]{0, 1, 2, 3, 4}, new double[]{-1, 6, 4, 0, 4});
//        .title("Area Chart")
//                 .xAxisTitle("X")
//                 .yAxisTitle("Y")
//                 .build();
        return chart;
    }

    public static void createFrame() {
        //creating and setting up window
        JFrame frame = new JFrame("Index Graph");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //adding chart into window
        JPanel chartPanel = new XChartPanel<XYChart>(createChart());
        frame.add(chartPanel, BorderLayout.CENTER);

        //labeling window
        JLabel label = new JLabel("Name of currency type will be here", SwingConstants.CENTER);
        frame.add(label, BorderLayout.SOUTH);

        //displaying the window
        frame.pack();
        frame.setVisible(true);
    }

    //    public static String parseJson(String filePath) {
//        StringBuilder valueBuilder = new StringBuilder();
//        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> valueBuilder.append(s).append("\n"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return valueBuilder.toString();
//    }
    private static String parseJson() {
        String jsonData = "";
        String filePath = "C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while (bufferedReader.ready() && (line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\r\n");

                jsonData = stringBuilder.toString();
            }
        } catch (IOException e) {
            System.out.println("Exception");
//                e.printStackTrace();
        }

        //creating Json object
        JSONObject obj = new JSONObject(jsonData);
//          System.out.println("23 March 2019: $" + obj.getJSONObject("Time Series FX (Daily)").getJSONObject("2019-03-23").getString("1. open"));
        //getting data from Json
        return obj.getJSONObject("Time Series FX (Daily)").getJSONObject("2019-03-23").getString("1. open");
    }

}