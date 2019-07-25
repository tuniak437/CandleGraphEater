package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class IndexGraph {

  private static final Logger log = LogManager.getLogger(IndexGraph.class.getName());

  public static void main(String[] args) {
    DataHandler myDataHandler = new DataHandler();
    JFrame jFrame = UIHandler.createFrame();

    JsonHandler jsonHandler =
        new JsonHandler(
            "C:\\Users\\Tuna-NB\\IdeaProjects\\index-graph-eater\\src\\main\\java\\cz\\tuniak\\query.json",
            "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR&to_symbol=USD&apikey=demo");

    // if parse json is null create window with message
    // else run the program
    myDataHandler.parseNewData(jsonHandler.parseJson());
    UIHandler.addDataToFrame(jFrame, myDataHandler.getDataMap());

    // print log into console and .log file
    // configure .properties so it makes sense
    //        log.debug("Debug message");
    //        log.info("Info log");
    //        log.error("Error message");

    //        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    //        Thread windowThread = new Thread(() ->
    //                 UIHandler.loadingSign());
    //                UIHandler.showMessage(UIHandler.createFrame(), "Loading...");
    //                JFrame load = UIHandler.loadingSign();
    //
    //        windowThread.start();
    // create log file with exception messages
  }
}
