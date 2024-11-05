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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

//Testing branching and main
public class MainActivity extends AppCompatActivity
        implements EntryListFragment.Callbacks {



    private static final int REQUEST_CODE = 2;
    public static final String KEY_ENTRY_ID = "KEY_ENTRY_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;});


        Fragment currentFragment = getSupportFragmentManager()
            .findFragmentById(R.id.fragment_container_view);
        if (currentFragment == null) {
        Fragment fragment = new EntryListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit();
        }
    }
    @Override
    public void onEntrySelected (UUID entryId){
        Bundle args = new Bundle();
        args.putSerializable(KEY_ENTRY_ID, entryId);

        Fragment fragment = new EntryDetailsFragment();
        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit();
    }

}