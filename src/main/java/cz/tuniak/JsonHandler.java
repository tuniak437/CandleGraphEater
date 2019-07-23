package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class JsonHandler {
    private static final Logger log = LogManager.getLogger(JsonHandler.class);
    private Path filePath;
    private URL url;

    JsonHandler() throws MalformedURLException {
        this.filePath = Paths.get("C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json");
        this.url = new URL("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR&to_symbol=USD&apikey=demo");
    }

    JSONObject parseJson() {
        //replace jsonhandler with real exceptions
        // constructor with path and url
        //read from file function on catch Jsonhandler exception
        try {
            JSONObject jsonObject = readJsonData(url);
            Files.copy(url.openStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return jsonObject;
        } catch (IOException e) {
            log.error(e.getMessage());
            return readJsonData(filePath);
        }
    }

    //open stream for data to read method
    private static JSONObject readJsonData(URL url) {
        try (InputStream stream = url.openStream()) {
            return readInputDataStream(stream);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
    private static JSONObject readJsonData(Path path) {
        try (InputStream stream = Files.newInputStream(path)) {
            return readInputDataStream(stream);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private static JSONObject readInputDataStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null)
            stringBuilder.append(inputLine).append("\n");
        return new JSONObject(stringBuilder.toString());
    }

}
