import cz.tuniak.IndexGraph;
import org.json.JSONObject;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoadDataTest {
    @Test
    public void compareJsonObject() {
        JSONObject query = new JSONObject("{\"Time Series FX (Daily)\": {\"2019-06-13\": {\"1. open\": \"1.1310\",\"2. high\": \"1.1315\",\"3. low\": \"1.1269\",\"4. close\": \"1.1273\"}}}");
//        IndexGraph.loadData(query);
        //Integer = number of month || String = (open, high, low, close)
        //LocalDate = full date in format "yyyy-MM-dd" || Double = values
        TreeMap<LocalDate, Double> open = new TreeMap<>(){{put(LocalDate.of(2019,6,13), 1.1310);}};
        TreeMap<LocalDate, Double> high = new TreeMap<>(){{put(LocalDate.of(2019,6,13), 1.1315);}};
        TreeMap<LocalDate, Double> low = new TreeMap<>(){{put(LocalDate.of(2019,6,13), 1.1269);}};
        TreeMap<LocalDate, Double> close = new TreeMap<>(){{put(LocalDate.of(2019,6,13), 1.1273);}};

        HashMap<String, TreeMap<LocalDate, Double>> openHash = new HashMap<>() {{put("open", open); put("high", high); put("low", low); put("close", close);}};

        HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> ugh = new HashMap<>() {{put(6, openHash);}};


        assertEquals(ugh, IndexGraph.loadData(query));

        }
}
