package cz.tuniak;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class JsonHandler {

    public static JSONObject parseJson() throws IOException {
        Path filePath = Paths.get("C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json");
        URL url = new URL("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR&to_symbol=USD&apikey=demo");

        try {
            JSONObject jsonObject = readJsonData(url);
            Files.copy(url.openStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
            return jsonObject;
        } catch (IOException e) {
            return readJsonData(filePath);
        }
    }

    //open stream for data to read method
    private static JSONObject readJsonData(URL url) throws IOException {
        InputStream stream = url.openStream();
        try (stream) {
            return readInputDataStream(stream);
        }
    }
    private static JSONObject readJsonData(Path path) throws IOException {
        InputStream stream = Files.newInputStream(path);
        try (stream) {
            return readInputDataStream(stream);
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
