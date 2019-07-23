package cz.tuniak;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChartData {
    private List<LocalDate> day;
    private List<Double> open;
    private List<Double> high;
    private List<Double> low;
    private List<Double> close;
    private boolean isSorted;

    public ChartData() {
        this.day = new ArrayList<>();
        this.open = new ArrayList<>();
        this.high = new ArrayList<>();
        this.low = new ArrayList<>();
        this.close = new ArrayList<>();
    }

    public List<LocalDate> getDay() {
        sortList();
        return day;
    }

    public List<Double> getOpen() {
        sortList();
        return open;
    }

    public List<Double> getHigh() {
        sortList();
        return high;
    }

    public List<Double> getLow() {
        sortList();
        return low;
    }

    public List<Double> getClose() {
        sortList();
        return close;
    }

    void addNewValues(LocalDate date, JSONObject jsonObject) {
        day.add(date);
        open.add(jsonObject.getDouble("1. open"));
        high.add(jsonObject.getDouble("2. high"));
        low.add(jsonObject.getDouble("3. low"));
        close.add(jsonObject.getDouble("4. close"));
        isSorted = false;
    }

    private void sortList() {
        if (!isSorted) {
            Collections.sort(day);
            Collections.sort(open);
            Collections.sort(high);
            Collections.sort(low);
            Collections.sort(close);
            isSorted = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartData chartData = (ChartData) o;

        if (!day.equals(chartData.day)) return false;
        if (!open.equals(chartData.open)) return false;
        if (!high.equals(chartData.high)) return false;
        if (!low.equals(chartData.low)) return false;
        return close.equals(chartData.close);
    }

    @Override
    public int hashCode() {
        int result = day.hashCode();
        result = 31 * result + open.hashCode();
        result = 31 * result + high.hashCode();
        result = 31 * result + low.hashCode();
        result = 31 * result + close.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ChartData{" +
                "day=" + day +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                '}';
    }
}
