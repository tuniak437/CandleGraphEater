package cz.tuniak;

import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class IndexGraph {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println(threadSet);
        new MyThread().start();

        //global exception handler, define messages for every exception
        JSONObject jsonDataObject;
        try {
            jsonDataObject = JsonHandler.parseJson();
            HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> data = loadData(jsonDataObject);
            UIHandler.createFrame(data);
        } catch (IOException e) {
            System.out.println("ni!");
        }
    }

    private static HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> loadData(JSONObject jsonDataObject) {
        //HashMap<YearMonth, ChartData>
        //ChartData variables (private attributes)= days, open, high, low, close

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
