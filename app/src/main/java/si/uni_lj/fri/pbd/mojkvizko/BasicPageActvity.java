package si.uni_lj.fri.pbd.mojkvizko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BasicPageActvity extends AppCompatActivity {
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_page);

        String user_mail = getIntent().getStringExtra("EXTRA_MAIL");
        name = (TextView) findViewById(R.id.textMail);
        name.setText(user_mail);

    }


    public void mojiKvizi(View view) {
        Intent intent = new Intent(this, MojiKviziActivity.class);
        startActivity(intent);

    }


}