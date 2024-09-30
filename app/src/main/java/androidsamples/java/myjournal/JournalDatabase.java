package androidsamples.java.myjournal;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {JournalEntity.class}, version = 1, exportSchema = false)
@TypeConverters(JournalTypeConverters.class)
public abstract class JournalDatabase extends RoomDatabase {
    public abstract JournalEntryDao journalentryDao();

}