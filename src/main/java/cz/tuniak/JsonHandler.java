package cz.tuniak;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

class JsonHandler {

    static JSONObject parseJson() {

        Path filePath = Paths.get("C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json");
        try {
            URL url = new URL("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR&to_symbol=USD&apikey=demo");
            URL anotherUrl = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
                stringBuilder.append(inputLine).append("\n");
            bufferedReader.close();

            //rewrites url page to query.json file inside project
            Files.copy(url.openStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
            return new JSONObject(stringBuilder.toString());

        } catch (MalformedURLException error) {
            System.out.println("Malformed url link.");
        } catch (Exception exception) {
            System.out.println("Url link is not correct. Creating chart from query.json file.");

            StringBuilder valueBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
                stream.forEach(s -> valueBuilder.append(s).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //creates from query.json file and returns Json object
            return new JSONObject(valueBuilder.toString());
        }
        return null;
    }
}
