package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

class DataHandler {
    private static final Logger log = LogManager.getLogger(JsonHandler.class);

            //int = year, Month = ENUM Month, ChartData = values
    private Map<Integer, TreeMap<Month, ChartData>> dataMap = new HashMap<>();

    void addNewEntries(JSONObject jsonObject) {
        JSONObject timeSeries = jsonObject.getJSONObject("Time Series FX (Daily)");

        ArrayList<String> dateValues = new ArrayList<>();

        for (Iterator<String> it = timeSeries.keys(); it.hasNext();) {
            String date = it.next();
            dateValues.add(date);
        }
        Collections.sort(dateValues);
        for (String date : dateValues) {
            LocalDate day = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            getChartData(day).addNewValues(day, timeSeries.getJSONObject(date));
        }

        //add debug log
    }

    private ChartData getChartData(LocalDate date) {
        TreeMap<Month, ChartData> months = dataMap.computeIfAbsent(date.getYear(), (x) -> new TreeMap<>());

        return months.computeIfAbsent(date.getMonth(), (x) -> new ChartData());
    }

    Map<Integer, TreeMap<Month, ChartData>> getDataMap() {
        return dataMap;
    }
}
