package androidsamples.java.myjournal;

import static androidx.core.app.PendingIntentCompat.getActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.util.UUID;

public class EntryDetailsFragment extends Fragment {

    private JournalEntry mEntry;
    private TextView mEditTitle;
    private TextView mEditDuration;
    private EntryDetailsViewModel mEntryDetailsViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEntryDetailsViewModel = new ViewModelProvider(requireActivity())
                .get(EntryDetailsViewModel.class);

        //This is something i added
        if(getArguments()!=null) {
            UUID entryId = (UUID) getArguments().
                    getSerializable(MainActivity.KEY_ENTRY_ID);
            //Log.d("EntryDetailsFragment", "Loading entry: " + entryId);

            mEntryDetailsViewModel.loadEntry(entryId);
        }
        else
        {
            mEntry = new JournalEntry("", 0);
            mEntryDetailsViewModel.saveEntry(mEntry);
        }
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entry_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton saveButton = view.findViewById(R.id.save);

        mEditTitle = view.findViewById(R.id.title);
        mEditDuration = view.findViewById(R.id.duration);
        saveButton.setOnClickListener(v -> saveEntry(view));
        mEntryDetailsViewModel.getEntryLiveData().observe(requireActivity(),
                entry -> {
                    this.mEntry = entry;
                    updateUI();
                });
    }

    private void updateUI(){
        if (mEntry != null) {
            mEditTitle.setText(mEntry.getTitle());
            mEditDuration.setText(String.valueOf(mEntry.getDuration()));
        }
        Log.d("EntryDetailsFragment", "updateUI");
    }

    private void saveEntry(View view) {

        if(TextUtils.isEmpty(mEditDuration.getText().toString()))
        {
            Toast.makeText(requireActivity(), "Duration Not Written", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(mEditTitle.getText().toString()))
        {
            Toast.makeText(requireActivity(), "Title Not Written", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.d("EntryDetailsFragment", mEditTitle.getText().toString());
//            mEntry = new JournalEntry(mEditTitle.getText().toString(), Integer.parseInt(mEditDuration.getText()
//                    .toString()));
            mEntry.setTitle(mEditTitle.getText().toString());

            mEntry.setDuration(Integer.parseInt(mEditDuration.getText()
                    .toString()));
            mEntryDetailsViewModel.saveEntry(mEntry);

            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        }
//        Log.d("EntryDetailsFragment", "Saving entry");

    }

}
