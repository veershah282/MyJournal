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
    private static JournalRepository instance;
    private final Executor mexecutor = Executors.newSingleThreadExecutor();
    //why shouldn't this be private;
    public JournalRepository(Context context){
       JournalDatabase db = Room.databaseBuilder(
               context.getApplicationContext(),
               JournalDatabase.class,
               DB_TABLE
       ).build();
    mJournalEntryDao = db.journalentryDao();
    //now you can access all of dao methods through mJournalEntryDao;
    }
    public static void init(Context context){
        if(instance==null)
            instance= new JournalRepository(context);

    }
    public static JournalRepository getInstance(){
        if(instance==null)
            Log.d("JournalRepository", "I don't know at this point");
        return instance;
    }
    public LiveData<List<JournalEntity>> getAllEntries(){
        return mJournalEntryDao.getAllEntries();
    }
    public void insert(JournalEntity entry)
    {
        mexecutor.execute(()->mJournalEntryDao.insert(entry));
    }

}
