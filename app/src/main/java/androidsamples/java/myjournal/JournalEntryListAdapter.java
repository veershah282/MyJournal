package androidsamples.java.myjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JournalEntryListAdapter
        extends RecyclerView.Adapter<JournalEntryListAdapter.EntryViewHolder> {

    private TextView mTxtTitle;
    private TextView mTxtDuration;
    private final LayoutInflater mInflater;
    private List<JournalEntity> mEntries;

    public JournalEntryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        View itemView = mInflater.inflate(R.layout.journal_item,
                parent,
                false);
        return new EntryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder,
                                 int position) {
        if (mEntries != null) {
            JournalEntity current = mEntries.get(position);
            holder.mTxtTitle.setText(current.title());
            holder.mTxtDuration.setText(Integer.toString(current.dur()));
        }
    }

    class EntryViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTxtTitle;
        private final TextView mTxtDuration;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtTitle = itemView.findViewById(R.id.txt_item_title);
            mTxtDuration = itemView.findViewById(R.id.txt_item_duration);
        }

    }

        @Override
        public int getItemCount() {
            return (mEntries == null) ? 0 : mEntries.size();
        }

        public void setEntries(List<JournalEntity> entries) {
            mEntries = entries;
            notifyDataSetChanged();
    }
}