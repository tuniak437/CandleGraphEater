package cz.tuniak;

import cz.tuniak.expections.JsonHandlerException;

public class IndexGraph {

//    private static final Logger log = LogManager.getLogger(IndexGraph.class.getName());

    public static void main(String[] args) {

//        JFrame jFrame = UIHandler.createFrame();
//        UIHandler.addDataToFrame(jFrame);

        DataHandler myDataHandler = new DataHandler();

        try {
            myDataHandler.addNewEntries(JsonHandler.parseJson());
        } catch (JsonHandlerException e) {
            e.printStackTrace();
        }


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
