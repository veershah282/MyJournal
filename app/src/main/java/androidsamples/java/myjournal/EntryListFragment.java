package androidsamples.java.myjournal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

public class EntryListFragment extends Fragment {
    private EntryListViewModel mEntryListViewModel;
    private Callbacks mCallbacks;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEntryListViewModel = new ViewModelProvider(this)
                .get(EntryListViewModel.class);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            mCallbacks = (Callbacks) context;
        } else {
            throw new IllegalStateException("Host activity must implement Callbacks interface");
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry_list, container, false);

        view.findViewById(R.id.floatingActionButton)
                .setOnClickListener(this::addNewEntry);

        RecyclerView entriesList = view.findViewById(R.id.recyclerView);
        entriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        JournalEntryListAdapter adapter = new JournalEntryListAdapter(getActivity(), mCallbacks);
        entriesList.setAdapter(adapter);

        mEntryListViewModel.getAllEntries()
                .observe(requireActivity(), adapter::setEntries);

        return view;
    }

    private void addNewEntry(View view) {


        JournalEntry entry = new JournalEntry("New Entry", 0);
        mEntryListViewModel.insert(entry);
        Log.d("EntryListFragment", "Adding new entry");
//        Fragment fragment = new EntryDetailsFragment();
//        requireActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container_view, fragment)
//                .addToBackStack(null)
//                .commit();
        Toast.makeText(getActivity(), "HEy", Toast.LENGTH_SHORT).show();
    }

    interface Callbacks {
        void onEntrySelected(UUID id);
    }
    public class JournalEntryListAdapter
            extends RecyclerView.Adapter<JournalEntryListAdapter.EntryViewHolder> {

        private final LayoutInflater mInflater;
        private List<JournalEntry> mEntries;
        private Callbacks mCallbacks = null;

        public JournalEntryListAdapter(Context context, Callbacks callbacks) {
            mInflater = LayoutInflater.from(context);
            mCallbacks = callbacks;
        }

        @NonNull @Override
        public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.view_holder,
                    parent,
                    false);
            return new EntryViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull EntryViewHolder holder,
                                     int position) {
            if (mEntries != null) {
                JournalEntry current = mEntries.get(position);
                holder.mTxtTitle.setText(current.getTitle());
                holder.mTxtDuration.setText(Integer.toString(current.getDuration()));
                holder.mEntry = current;
            }
        }


        class EntryViewHolder extends RecyclerView.ViewHolder {
            private final TextView mTxtTitle;
            private final TextView mTxtDuration;
            private JournalEntry mEntry;

            public EntryViewHolder(@NonNull View itemView) {
                super(itemView);
                mTxtTitle = itemView.findViewById(R.id.txt_item_title);
                mTxtDuration = itemView.findViewById(R.id.txt_item_duration);

                itemView.setOnClickListener(this::launchJournalEntryFragment);
            }
            //GEMINI suggested
            private void launchJournalEntryFragment(View v) {
                mCallbacks.onEntrySelected(mEntry.getUid());
            }

        }

        @Override
        public int getItemCount() {
            return (mEntries == null) ? 0 : mEntries.size();
        }

        public void setEntries(List<JournalEntry> entries) {
            mEntries = entries;
            notifyDataSetChanged();
        }

    }
}
