package androidsamples.java.myjournal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class EntryListViewModel extends ViewModel {

    private final JournalRepository mRepository;

    public EntryListViewModel() {
        mRepository = JournalRepository.getInstance();
    }
    public void insert(JournalEntry entry) {
        mRepository.insert(entry);
    }
    public LiveData<List<JournalEntry>> getAllEntries() {
        return mRepository.getAllEntries();
    }

}
