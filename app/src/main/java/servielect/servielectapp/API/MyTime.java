package servielect.servielectapp.API;

import android.app.Application;
import android.os.SystemClock;


public class MyTime extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
