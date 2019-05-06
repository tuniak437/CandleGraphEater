package cz.tuniak;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class IndexGraph {

    public static void main(String[] args) {
        JSONObject jsonDataObject = JsonHandler.parseJson();
        HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> data = loadData(jsonDataObject);
        UIHandler.createFrame(data);

    }

    public static HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> loadData(JSONObject jsonDataObject) {
        //creates new instance of DataHandler
        DataHandler myHandler = new DataHandler();
        //gets variable JSONObject timeSeries
        JSONObject timeSeries = jsonDataObject.getJSONObject("Time Series FX (Daily)");
        //iterates through the whole file taking keys of timeSeries object saving it in a String date variable
        for (Iterator<String> it = timeSeries.keys(); it.hasNext(); ) {
            String date = it.next();

            //uses String date variable to get open, high, low, close values from JSONObject timeSeries
            myHandler.addNewEntry(date, timeSeries.getJSONObject(date));
        }
        //returns HashMap with every value from selected month
        return myHandler.months;
    }

}
