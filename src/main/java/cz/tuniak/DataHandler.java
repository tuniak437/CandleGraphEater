package cz.tuniak;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class DataHandler {
    //Integer = number of month || String = (open, high, low, close)
    //LocalDate = full date in format "yyyy-MM-dd" || Double = values
    HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> months = new HashMap<>();

    /**
     * Iterates one time and adds new entry to every TreeMap.
     *
     * @param date       String representation of date. Always in format `YYYY-MM-DD`.
     * @param jsonCandle Double representation of candle values(open, high, low, close).
     */
    public void addNewEntry(String date, JSONObject jsonCandle) {
        //creates LocalDate variable from String date in format "yyyy-MM-dd"
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //adds another values to HashMap months if HashMap already has a number of month in it
        if (this.months.containsKey(parsedDate.getMonthValue())) {
            this.months.get(parsedDate.getMonthValue()).get("open").put(parsedDate, jsonCandle.getDouble("1. open"));
            this.months.get(parsedDate.getMonthValue()).get("high").put(parsedDate, jsonCandle.getDouble("2. high"));
            this.months.get(parsedDate.getMonthValue()).get("low").put(parsedDate, jsonCandle.getDouble("3. low"));
            this.months.get(parsedDate.getMonthValue()).get("close").put(parsedDate, jsonCandle.getDouble("4. close"));
        } else {
            //else it creates a new inner HashMap to put values in
            //uses TreeMap for LocalDate to sort it
            HashMap<String, TreeMap<LocalDate, Double>> candles = new HashMap<>();

            candles.put("open", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("1. open"))));
            candles.put("high", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("2. high"))));
            candles.put("low", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("3. low"))));
            candles.put("close", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("4. close"))));

            //puts this iteration into HashMap months
            this.months.put(parsedDate.getMonthValue(), candles);
        }
    }

}
