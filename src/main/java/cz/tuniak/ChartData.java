package cz.tuniak;

import java.time.YearMonth;
import java.util.ArrayList;

public class ChartData {
    int days;
    YearMonth yearMonth;
    ArrayList<Double> openValues;
    ArrayList<Double> highValues;
    ArrayList<Double> lowValues;
    ArrayList<Double> closeValues;

    public ChartData(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
        this.days = yearMonth.lengthOfMonth();
    }

    public ArrayList<Double> getDays() {
        ArrayList<Double> days = new ArrayList<Double>();
        for(int i=0; i < yearMonth.lengthOfMonth(); i++){
            double o = (double) i+1;
            days.add(o);
        }
        return days;
    }

    public ArrayList<Double> getOpenValues() {

        return openValues;
    }

    public ArrayList<Double> getHighValues() {
        return highValues;
    }

    public ArrayList<Double> getLowValues() {
        return lowValues;
    }

    public ArrayList<Double> getCloseValues() {
        return closeValues;
    }
}
