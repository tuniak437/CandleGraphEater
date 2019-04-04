package cz.tuniak;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

class JsonHandler {

    //Json object
    static JSONObject parseJson() {

        String filePath = "C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json";

        StringBuilder valueBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> valueBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creates Json object
        JSONObject jsonDataObject = new JSONObject(valueBuilder.toString());
        //getting data from Json
        return jsonDataObject;

    }

}
