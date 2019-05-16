package cz.tuniak;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

class JsonHandler {

    //Json object
    static JSONObject parseJson() throws Exception {

//        String filePath = "C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json";
//
//        StringBuilder valueBuilder = new StringBuilder();
//        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> valueBuilder.append(s).append("\n"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //creates and returns Json object
//        return new JSONObject(valueBuilder.toString());

        URL url = new URL("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR&to_symbol=USD&apikey=demo");
        URL anotherUrl = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo");
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null)
            stringBuilder.append(inputLine).append("\n");
        bufferedReader.close();

        return new JSONObject(stringBuilder.toString());
    }


}
