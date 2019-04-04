package cz.tuniak;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

class JsonHandler {

    //Json object
    static JSONObject parseJson() {

        String jsonData = "";
        String filePath = "C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json";

//       // working solution
//        try {
//            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
//            jsonData = new String(encoded, StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
        // working solution
        StringBuilder valueBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> valueBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creates Json object
        JSONObject obj = new JSONObject(valueBuilder.toString());
//          System.out.println("23 March 2019: $" + obj.getJSONObject("Time Series FX (Daily)").getJSONObject("2019-03-23").getString("1. open"));
        //getting data from Json
        return obj;

    }
        //creates arraylist of string values
    static ArrayList<String> createMonthString() {
        ArrayList<String> daysInMonth = new ArrayList<String>();
        int day = 0;
        while(day != 21) {
            day++;
            String month = "2019-03-";
            if(day < 10) {
                String dayString = "0" + day;
                month += dayString;
            } else {
                month += day;
            }
            daysInMonth.add(month);
        }
        return daysInMonth;
    }

    static void getOpenDataValues(JSONObject obj) {
        createMonthString().forEach((n) -> yoloJson (n));

    }

    static ArrayList<String> yoloJson(String n) {
        String nameOfGoods = "Time Series FX (Daily)";
        ArrayList<String> currencyValuesFromJson = new ArrayList<>();
        //n = date String
        String value = parseJson().getJSONObject(nameOfGoods).getJSONObject(n).getString("1. open");
        System.out.println(value);
        currencyValuesFromJson.add(value);

        return currencyValuesFromJson;
    }
}
