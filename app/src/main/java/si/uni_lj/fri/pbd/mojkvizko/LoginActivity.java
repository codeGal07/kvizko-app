package si.uni_lj.fri.pbd.mojkvizko;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {
    EditText mail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void loginUser(View view) {

        mail = findViewById(R.id.username);
        Intent intent = new Intent(this, BasicPageActvity.class);
        intent.putExtra("EXTRA_MAIL", mail.getText().toString());
        startActivity(intent);

    }
}
