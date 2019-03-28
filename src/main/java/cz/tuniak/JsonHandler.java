package cz.tuniak;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

class JsonHandler {
    static String parseJson() {

        String jsonData = "";
        String filePath = "C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json";

//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
//            StringBuilder stringBuilder = new StringBuilder();
//            String line = "";
//
//            while (bufferedReader.ready() && (line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line + "\r\n");
//
//                jsonData = stringBuilder.toString();
//            }
//        } catch (IOException e) {
//            System.out.println("Exception");
////                e.printStackTrace();
//        }
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

        //creating Json object
        JSONObject obj = new JSONObject(valueBuilder.toString());
//          System.out.println("23 March 2019: $" + obj.getJSONObject("Time Series FX (Daily)").getJSONObject("2019-03-23").getString("1. open"));
        //getting data from Json
        return obj.getJSONObject("Time Series FX (Daily)").getJSONObject("2019-03-23").getString("1. open");
    }
}
