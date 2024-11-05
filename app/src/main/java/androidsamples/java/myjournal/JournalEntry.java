package androidsamples.java.myjournal;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;
//the column names are kept private because you don't want anyone to access the table
//directly.
@Entity(tableName = "journal_table")
public class JournalEntry {
    @PrimaryKey
    @ColumnInfo(name="id")
    @NonNull
    private UUID mUid;

    @ColumnInfo(name = "title")
     private String mTitle;

    @ColumnInfo(name = "duration")
    private int mDuration;


    public JournalEntry(@NonNull String title, int duration) {
        mUid = UUID.randomUUID();
        mTitle = title;
        mDuration = duration;
    }
  //getters and setters
    @NonNull
    public UUID getUid() {
        return mUid;
    }
    public String getTitle() {
        return mTitle;
    }
    public int getDuration() {
        return mDuration;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public void setDuration(int duration) {
        mDuration = duration;
    }
    public void setUid(@NonNull UUID uid) { mUid = uid; }

}
