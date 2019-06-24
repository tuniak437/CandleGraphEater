package cz.tuniak;

import javax.swing.*;

public class IndexGraph {

//    private static final Logger log = LogManager.getLogger(IndexGraph.class.getName());

    public static void main(String[] args) {

        JFrame jFrame = UIHandler.createFrame();
        UIHandler.addDataToFrame(jFrame);

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

    // not needed anymore :((

//    public static HashMap<Integer, HashMap<String, TreeMap<LocalDate, Double>>> loadData(JSONObject jsonDataObject) {
//        //creates new instance of DataHandler
//        DataHandler myHandler = new DataHandler();
//        //gets variable JSONObject timeSeries
//        JSONObject timeSeries = jsonDataObject.getJSONObject("Time Series FX (Daily)");
//        //iterates through the whole file taking keys of timeSeries object saving it in a String date variable
//        for (Iterator<String> it = timeSeries.keys(); it.hasNext();) {
//            String date = it.next();
//
//            //uses String date variable to get open, high, low, close values from JSONObject timeSeries
//            myHandler.addNewEntry(date, timeSeries.getJSONObject(date));
//        }
//        //returns HashMap with every value from selected month
//        return myHandler.months;
//    }
}
