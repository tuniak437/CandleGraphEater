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

  private Map<Integer, TreeMap<Month, ChartData>> dataMap = new HashMap<>();

  void parseNewData(JSONObject jsonObject) {
    JSONObject timeSeries = jsonObject.getJSONObject("Time Series FX (Daily)");
    // using ArrayList dateValue to store and sort String values parsed from iterator
    ArrayList<String> dateValues = new ArrayList<>();

    for (Iterator<String> it = timeSeries.keys(); it.hasNext(); ) {
      String date = it.next();
      dateValues.add(date);
    }
    // sorting dateValues to prevent shuffled days in chart
    Collections.sort(dateValues);
    for (String date : dateValues) {
      LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      getChartData(localDate).addNewValues(localDate, timeSeries.getJSONObject(date));
    }
  }

  /**
   * Obtains Integer year from date parameter, if Map<Integer, TreeMap<Month, ChartData>> dataMap
   * doesn't already contains that Integer, it creates new year key from it with empty value.
   *
   * <p>Obtains Month enum month from date parameter, if TreeMap<Month, ChartData> months doesn't
   * already contains that Month month, it creates new month key from it with empty value.
   *
   * @param date year-month-day object in "yyyy-MM-dd" pattern.
   * @return Empty ChartData from exact Integer year key and Month month key from parameter which is
   *     later filled with corresponding data in ChartData class.
   */
  // creates Java object with JSON
  // converts JSON to Java objects
  private ChartData getChartData(LocalDate date) {
    TreeMap<Month, ChartData> months =
        dataMap.computeIfAbsent(date.getYear(), (x) -> new TreeMap<>());

    return months.computeIfAbsent(date.getMonth(), (x) -> new ChartData());
  }

  Map<Integer, TreeMap<Month, ChartData>> getDataMap() {
    return dataMap;
  }
}
