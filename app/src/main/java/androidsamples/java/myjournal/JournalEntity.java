package androidsamples.java.myjournal;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;
//the column names are kept private because you don't want anyone to access the table
//directly.
@Entity(tableName = "journal_table")
public class JournalEntity {
    @PrimaryKey
    @ColumnInfo(name="uuid")
    @NonNull
    private UUID uuid;

    @ColumnInfo(name = "title")
     private String title;

    @ColumnInfo(name = "dur")
    private int dur;

    public JournalEntity(String title, int dur) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.dur = dur;
    }
    public String title() {
        return title;
    }

    public int dur() {
        return dur;
    }

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }




}
