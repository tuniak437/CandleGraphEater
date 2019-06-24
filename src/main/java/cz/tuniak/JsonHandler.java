package cz.tuniak;

import cz.tuniak.expections.JsonHandlerException;
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

class JsonHandler extends Exception{
    //Logger jsonLogger = LoggerHandler.createLogger(); ?
    static final Logger log = LogManager.getLogger(JsonHandler.class);

    static JSONObject parseJson() throws MalformedURLException, JsonHandlerException {
        Path filePath = Paths.get("C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json");
        URL url = new URL("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR&to_symbol=USD&apikey=demo");

        try {
            JSONObject jsonObject = readJsonData(url);
//            Files.copy(url.openStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return jsonObject;
        } catch (JsonHandlerException e) {
            return readJsonData(filePath);
        }
    }

    //open stream for data to read method
    private static JSONObject readJsonData(URL url) throws JsonHandlerException {
        try (InputStream stream = url.openStream()) {
            return readInputDataStream(stream);
        } catch (IOException e) {
//            log.error(e.getMessage());
            throw new JsonHandlerException("owo", e);
        }
    }
    private static JSONObject readJsonData(Path path) throws JsonHandlerException {
        try (InputStream stream = Files.newInputStream(path)) {
            return readInputDataStream(stream);
        } catch (IOException e) {
//            log.error(e.getMessage());
            throw new JsonHandlerException("ugh",e);
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
