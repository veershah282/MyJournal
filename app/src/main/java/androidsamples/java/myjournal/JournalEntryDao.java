package androidsamples.java.myjournal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JournalEntryDao{
    @Insert
    public void insert(JournalEntity J);

    @Query("Select* from journal_table order by dur ASC")
    public LiveData<List<JournalEntity>> getAllEntries();
}
