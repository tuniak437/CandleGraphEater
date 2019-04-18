package cz.tuniak;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class DataHandler {

    HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> months = new HashMap<>();

    /**
     * Iterates one time and adds new entry to every Arraylist.
     *
     * @param date
     *          String representation of date. Always in format `YYYY-MM-DD`.
     * @param jsonCandle
     *          Double representation of candle values(open, high, low, close).
     */
    public void addNewEntry(String date, JSONObject jsonCandle) {
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (this.months.containsKey(parsedDate.getMonthValue())) {
            this.months.get(parsedDate.getMonthValue()).get("open").put(parsedDate, jsonCandle.getDouble("1. open"));
            this.months.get(parsedDate.getMonthValue()).get("high").put(parsedDate, jsonCandle.getDouble("2. high"));
            this.months.get(parsedDate.getMonthValue()).get("low").put(parsedDate, jsonCandle.getDouble("3. low"));
            this.months.get(parsedDate.getMonthValue()).get("close").put(parsedDate, jsonCandle.getDouble("4. close"));
        } else {
            HashMap<String, TreeMap<LocalDate, Double>> candles = new HashMap<>();

            candles.put("open", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("1. open"))));
            candles.put("high", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("2. high"))));
            candles.put("low", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("3. low"))));
            candles.put("close", new TreeMap<>(Map.of(parsedDate, jsonCandle.getDouble("4. close"))));

            this.months.put(parsedDate.getMonthValue(), candles);
        }
    }

}
