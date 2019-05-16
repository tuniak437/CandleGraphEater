package cz.tuniak;

import java.time.YearMonth;
import java.util.ArrayList;

public class ChartData {
    private ArrayList<YearMonth> yearMonths;
    private ArrayList<Double> openValues;
    private ArrayList<Double> highValues;
    private ArrayList<Double> lowValues;
    private ArrayList<Double> closeValues;

    public ChartData(ArrayList<YearMonth> yearMonths) {
        this.yearMonths = yearMonths;
        this.openValues = openValues;
        this.highValues = highValues;
        this.lowValues = lowValues;
        this.closeValues = closeValues;
    }

    public ArrayList<YearMonth> getYearMonths() {
        return yearMonths;
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
