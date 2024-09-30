package androidsamples.java.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//Testing branching and main
public class MainActivity extends AppCompatActivity {

    private JournalViewModel mJournalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        JournalEntryListAdapter adapter = new JournalEntryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mJournalViewModel = new ViewModelProvider(this)
                .get(JournalViewModel.class);
        mJournalViewModel.getAllEntries()
                .observe(this,
                        (List<JournalEntity> entries) -> adapter.setEntries(entries));
    }

    /** @noinspection deprecation*/
    public void launchAddEntryActivity(View view) {
        Intent intent = new Intent(this, AddEntry.class);
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra("Title");
                int dur = data.getIntExtra("Dur", 0);
                mJournalViewModel.insert(new JournalEntity(title, dur));
            }
        }
    }
}