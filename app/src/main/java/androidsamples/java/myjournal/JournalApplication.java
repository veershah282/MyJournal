package androidsamples.java.myjournal;

import android.app.Application;
import android.content.Context;

public class JournalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JournalRepository.init(this);
    }
}
