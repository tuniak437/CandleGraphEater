package cz.tuniak;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

class ChartData {
    private JSONObject jsonObject;

    ChartData(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    private ArrayList<LocalDate> sortDayValues() {
        ArrayList<LocalDate> sortedDayValues = new ArrayList<>();
        JSONObject timeSeries = this.jsonObject.getJSONObject("Time Series FX (Daily)");
        for (Iterator<String> it = timeSeries.keys(); it.hasNext(); ) {
            String date = it.next();
            sortedDayValues.add(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        Collections.sort(sortedDayValues);
        return sortedDayValues;
    }

    ArrayList<Date> getDays() {
        ArrayList<LocalDate> sortedDayValues = sortDayValues();
        ArrayList<Date> days = new ArrayList<>();
        for (LocalDate day : sortedDayValues) {
            days.add(Date.from(day.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        return days;
    }

    ArrayList<Double> getOpenValues() {
        ArrayList<Double> openValues = new ArrayList<>();
        for (LocalDate day : sortDayValues()) {
            openValues.add(this.jsonObject.getJSONObject("Time Series FX (Daily)").getJSONObject(day.toString()).getDouble("1. open"));
        }
        return openValues;
    }

    ArrayList<Double> getHighValues() {
        ArrayList<Double> highValues = new ArrayList<>();
        for (LocalDate day : sortDayValues()) {
            highValues.add(this.jsonObject.getJSONObject("Time Series FX (Daily)").getJSONObject(day.toString()).getDouble("2. high"));
        }
        return highValues;
    }

    ArrayList<Double> getLowValues() {
        ArrayList<Double> lowValues = new ArrayList<>();
        for (LocalDate day : sortDayValues()) {
            lowValues.add(this.jsonObject.getJSONObject("Time Series FX (Daily)").getJSONObject(day.toString()).getDouble("3. low"));
        }
        return lowValues;
    }

    ArrayList<Double> getCloseValues() {
        ArrayList<Double> closeValues = new ArrayList<>();
        for (LocalDate day : sortDayValues()) {
            closeValues.add(this.jsonObject.getJSONObject("Time Series FX (Daily)").getJSONObject(day.toString()).getDouble("4. close"));
        }
        return closeValues;
    }
}
