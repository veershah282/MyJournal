package androidsamples.java.myjournal;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JournalRepository {
    private static final String DB_TABLE= "journal_table";
    private final JournalEntryDao mJournalEntryDao;
    private static JournalRepository sinstance;
    private final Executor mexecutor = Executors.newSingleThreadExecutor();

    private JournalRepository(Context context){
       JournalDatabase db = Room.databaseBuilder(
                           context.getApplicationContext(),
                           JournalDatabase.class,
                           DB_TABLE
                            )
               .fallbackToDestructiveMigration()
               .build();
        mJournalEntryDao = db.journalentryDao();
        //now you can access all of dao methods through mJournalEntryDao;
    }
    public static void init(Context context){
        if(sinstance==null) sinstance = new JournalRepository(context);
    }
    public static JournalRepository getInstance(){
        if(sinstance==null)
            throw new IllegalStateException("Repository not initialized");
        return sinstance;
    }
    public LiveData<List<JournalEntry>> getAllEntries(){
        return mJournalEntryDao.getAllEntries();
    }
    public void insert(JournalEntry entry)
    {
        mexecutor.execute(()->mJournalEntryDao.insert(entry));
    }

}
