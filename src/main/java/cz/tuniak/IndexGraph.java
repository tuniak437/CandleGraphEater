package cz.tuniak;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class IndexGraph {

    public static void main(String[] args) {
//        OHLCChart chart = ChartHandler.createChart();
//        UIHandler.createFrame(chart);
    //    System.out.println("23 March 2019: $" + obj.getJSONObject("Time Series FX (Daily)").getJSONObject("2019-03-23").getString("1. open"));
        JSONObject jsonDataObject = JsonHandler.parseJson();

        HashMap<Integer, HashMap<String, ArrayList<Double>>> data = loadData(jsonDataObject);
        System.out.println(data);
    }
    
    public static HashMap<Integer, HashMap<String, ArrayList<Double>>> loadData(JSONObject jsonDataObject) {
        DataHandler myHandler = new DataHandler();
        JSONObject timeSeries = jsonDataObject.getJSONObject("Time Series FX (Daily)");
        for (Iterator<String> it = timeSeries.keys(); it.hasNext(); ) {
            String date = it.next();

            myHandler.addNewEntry(date, timeSeries.getJSONObject(date));
        }
        return myHandler.months;
    }
}