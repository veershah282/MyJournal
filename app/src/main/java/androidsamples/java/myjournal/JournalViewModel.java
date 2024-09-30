package androidsamples.java.myjournal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class JournalViewModel extends ViewModel {
        private final JournalRepository mRepository;

        public JournalViewModel() {
            mRepository = JournalRepository.getInstance();
        }

        public LiveData<List<JournalEntity>> getAllEntries() {
            return mRepository.getAllEntries();
        }

        public void insert(JournalEntity entry) {
            mRepository.insert(entry);
        }
    }

