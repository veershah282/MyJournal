package androidsamples.java.myjournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.UUID;


public class JournalTypeConverters {
    @TypeConverter
    public UUID toUUID(@NonNull String uuid) {
        return UUID.fromString(uuid);
    }

    @TypeConverter
    public String fromUUID(@NonNull UUID uuid) {
        return uuid.toString();
    }
}