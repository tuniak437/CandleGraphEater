package cz.tuniak;

import org.json.JSONObject;
import org.knowm.xchart.OHLCChart;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class IndexGraph {

    public static void main(String[] args) {
        JSONObject jsonDataObject = JsonHandler.parseJson();
        HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> data = loadData(jsonDataObject);
        OHLCChart chart = ChartHandler.createChart(data);
        UIHandler.createFrame(chart);
    }
    
    public static HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> loadData(JSONObject jsonDataObject) {
        DataHandler myHandler = new DataHandler();
        JSONObject timeSeries = jsonDataObject.getJSONObject("Time Series FX (Daily)");
        for (Iterator<String> it = timeSeries.keys(); it.hasNext(); ) {
            String date = it.next();

            myHandler.addNewEntry(date, timeSeries.getJSONObject(date));
        }
        return myHandler.months;
    }
}