package cz.tuniak;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DataHandler {

    HashMap<Integer, HashMap<String, ArrayList<Double>>> months = new HashMap<>();

    /**
     * Splits date and takes only month part from it parsing as a Integer afterwards.
     *
     * @param date
     *          String representation of date. Always in format `YYYY-MM-DD`.
     * @return
     *          Integer representation of month.
     */
    private Integer parseDate(String date) {

        return Integer.parseInt(date.split("-")[1]);
    }

    /**
     * Iterates one time and adds new entry to every Arraylist.
     *
     * @param date
     *          String representation of date. Always in format `YYYY-MM-DD`.
     * @param jsonCandle
     *          Double representation of candle values(open, high, low, close).
     */
    public void addNewEntry(String date, JSONObject jsonCandle) {
        Integer month = parseDate(date);

        if (this.months.containsKey(month)) {
            this.months.get(month).get("open").add(jsonCandle.getDouble("1. open"));
            this.months.get(month).get("high").add(jsonCandle.getDouble("2. high"));
            this.months.get(month).get("low").add(jsonCandle.getDouble("3. low"));
            this.months.get(month).get("close").add(jsonCandle.getDouble("4. close"));
        } else {
            HashMap<String, ArrayList<Double>> candles = new HashMap<>();

            candles.put("open", new ArrayList<>(List.of(jsonCandle.getDouble("1. open"))));
            candles.put("high", new ArrayList<>(List.of(jsonCandle.getDouble("2. high"))));
            candles.put("low", new ArrayList<>(List.of(jsonCandle.getDouble("3. low"))));
            candles.put("close", new ArrayList<>(List.of(jsonCandle.getDouble("4. close"))));

            this.months.put(month, candles);
        }
    }


}
