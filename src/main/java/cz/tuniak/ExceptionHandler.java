package cz.tuniak;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.toString());
    }
}

class MyThread extends Thread {
    public MyThread() {
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(e.toString());
            }
        });
    }

    public void run() {
        throw new RuntimeException();
    }
}
