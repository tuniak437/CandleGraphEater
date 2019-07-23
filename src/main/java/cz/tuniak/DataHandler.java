package cz.tuniak;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class DataHandler {

    //int = year, String = Month, ChartData = values
    Map<Integer, HashMap<Month, ChartData>> dataMap = new HashMap<>();

    void addNewEntries(JSONObject jsonObject) {
        JSONObject timeSeries = jsonObject.getJSONObject("Time Series FX (Daily)");

        for (Iterator<String> it = timeSeries.keys(); it.hasNext();) {
            String date = it.next();
            LocalDate day = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            getChartData(day).addNewValues(day, timeSeries.getJSONObject(date));
        }

        //add debug log
    }

    ChartData getChartData(LocalDate date) {
        HashMap<Month, ChartData> months = dataMap.computeIfAbsent(date.getYear(), (x) -> new HashMap<>());
        return months.computeIfAbsent(date.getMonth(), (x) -> new ChartData());
    }

}
