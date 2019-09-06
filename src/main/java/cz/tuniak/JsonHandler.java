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

  // an alternative on how to deal with MalformedURLException
  //  JsonHandler(String filePath, String url) throws MalformedURLException {
  //    this.filePath = Paths.get(filePath);
  //    this.url = new URL(url);
  //  }

  JsonHandler(String filePath, String url) {
    this.filePath = Paths.get(filePath);
    try {
      this.url = new URL(url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method tries to read data from URL page. If it is successful, data is stored is .json file. In
   * case there is a problem with Internet connection this file is used to create chart instead.
   *
   * @return The JSONObject.
   */
  JSONObject parseJson() {
    try {
      JSONObject jsonObject = readJsonData(url);
      Files.copy(url.openStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
      return jsonObject;
    } catch (IOException e) {
      log.error(e.getMessage());
      try {
        return readJsonData(filePath);
      } catch (IOException ex) {
        log.fatal(ex.getMessage());
//        System.exit(1);
      }
    }
    return null;
  }

  /**
   * Reading JSON data using InputStream to get data from URL page.
   *
   * @param url URL page containing graph data in JSON format.
   * @return The JSONObject.
   */
  private static JSONObject readJsonData(URL url) throws IOException {
    InputStream stream = url.openStream();
    return readInputDataStream(stream);
  }

  /**
   * Reading JSON data from file using InputStream.
   *
   * @param path Path to directory where .json file is located.
   * @return The JSONObject.
   */
  private static JSONObject readJsonData(Path path) throws IOException {
    InputStream stream = Files.newInputStream(path);
    return readInputDataStream(stream);
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
