package cz.tuniak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.net.MalformedURLException;

public class IndexGraph {

    private static final Logger log = LogManager.getLogger(IndexGraph.class.getName());

    public static void main(String[] args) {

        DataHandler myDataHandler = new DataHandler();
        JFrame jFrame = UIHandler.createFrame();

        JsonHandler jsonHandler = null;
        try {
            jsonHandler = new JsonHandler();
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
        myDataHandler.addNewEntries(jsonHandler.parseJson());
        UIHandler.addDataToFrame(jFrame, myDataHandler.getDataMap());


//        System.out.println(myDataHandler.getDataMap());







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
        //create log file with exception messages
    }
}
