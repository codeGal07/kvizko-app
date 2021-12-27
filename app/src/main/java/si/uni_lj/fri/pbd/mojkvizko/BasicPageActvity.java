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

        String uporabnisko_ime = getIntent().getStringExtra("EXTRA_UPORABNISKO_IME");
        name = (TextView) findViewById(R.id.textMail);
        name.setText(uporabnisko_ime);

    }

    public void mojiKvizi(View view) {
        Intent intent = new Intent(this, MojiKviziActivity.class);
        intent.putExtra("EXTRA_UPORABNISKO_IME", name.getText().toString());
        startActivity(intent);

    }

    public void vsiKvizi(View view) {
        Intent intent = new Intent(this, MojiKviziActivity.class);
        intent.putExtra("EXTRA_UPORABNISKO_IME", "VSI_KVIZI");
        startActivity(intent);

    }

    public void ustvariNovKviz(View view) {
        Intent intent = new Intent(this, UstvariNovKvizActivity.class);
        intent.putExtra("EXTRA_UPORABNISKO_IME", name.getText().toString());
        startActivity(intent);
    }


}