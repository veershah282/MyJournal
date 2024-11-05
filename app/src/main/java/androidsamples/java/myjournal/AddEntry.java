package androidsamples.java.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddEntry extends AppCompatActivity {


    private EditText mTxtTitle;
    private EditText mTxtDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_entry_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mTxtTitle = findViewById(R.id.title);
        mTxtDuration = findViewById(R.id.duration);

    }


    public void launchMainActivity(View view) {
        String title = mTxtTitle.getText().toString();
        if(title == null){
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
        }
        int dur = Integer.parseInt(mTxtDuration.getText().toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Title", title);
        resultIntent.putExtra("Dur", dur);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}