import cz.tuniak.ChartData;
import org.json.JSONObject;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChartDataTests {
    private JSONObject query = new JSONObject("{\"Time Series FX (Daily)\": " +
            "{\"2019-06-13\": {\"1. open\": \"1.1310\",\"2. high\": \"1.1315\",\"3. low\": \"1.1269\",\"4. close\": \"1.1273\"}," +
            "\"2019-07-01\": {\"1. open\": \"1.1368\",\"2. high\": \"1.1371\",\"3. low\": \"1.1279\",\"4. close\": \"1.1286\"}}}");
    private ChartData myChartData = new ChartData(query);

    @Test
    public void chartDataDaysTest() {
        ArrayList<Date> dayList = new ArrayList<>();
        dayList.add(Date.from(LocalDate.of(2019,6,13).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dayList.add(Date.from(LocalDate.of(2019,7,1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        assertEquals(dayList, myChartData.getDays());
        }

    @Test
    public void chartDataMonthsTest() {
        ArrayList<Month> monthList = new ArrayList<>();
        monthList.add(Month.JUNE);
        monthList.add(Month.JULY);

        assertEquals(monthList, myChartData.getMonths());
    }

    @Test
    public void chartDataOpenValuesTest() {
        ArrayList<Double> openValuesList = new ArrayList<>();
        openValuesList.add(1.1310);
        openValuesList.add(1.1368);

        assertEquals(openValuesList, myChartData.getOpenValues());
    }

}
