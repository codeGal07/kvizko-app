package si.uni_lj.fri.pbd.mojkvizko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IzbranKvizActivity extends AppCompatActivity {

    TextView imeKviza;
    private TextView kateroVprasanjeOdKolkih;
    private TextView vprasanje;
    private TextView odgovor;



    private VprasanjeModel[] vprasanjeBank;
    private int currentIndex =0;
    private boolean isVprasanje = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbran_kviz);

        String ime_kviza = getIntent().getStringExtra("EXTRA_IME_KVIZA");
        imeKviza = (TextView) findViewById(R.id.ImeKviza);
        imeKviza.setText(ime_kviza);

        if (ime_kviza.equals("||. svetovna vojna")) {
            vprasanjeBank = new VprasanjeModel[]{
                    new VprasanjeModel("V kateri državi se je zgodil dan D?", "V Franciji"),
                    new VprasanjeModel("Kdo je bil zaveznik nacistične Nemčije?", "Italija in Japonska"),
                    new VprasanjeModel("Katerega leta se je začela 2. svetovna vojna?", "1939"),
            };
        }

        if (ime_kviza.equals("Prazgodovina")) {
            vprasanjeBank = new VprasanjeModel[]{
                    new VprasanjeModel("Kdo je avstrolopitek?", "Prvi človek al neki"),
                    new VprasanjeModel("Kdaj je bla kamena doma?", "Enkrat v prazgodovini"),
                    new VprasanjeModel("Kje so našli okostje avstrolopitka?", "V Afriki"),
            };
        }

        vprasanje = (TextView) findViewById(R.id.trenutnoVprasanje);
        vprasanje.setText(vprasanjeBank[currentIndex].getVprasanje());

        odgovor = (TextView) findViewById(R.id.trenutenOdgovor);
        odgovor.setText(vprasanjeBank[currentIndex].getOdgovor());

        kateroVprasanjeOdKolkih = (TextView) findViewById(R.id.kateroOdKolkih);
        kateroVprasanjeOdKolkih.setText(String.valueOf(currentIndex) + "/" + String.valueOf(vprasanjeBank.length));

    }



    public void updateVpasanje(View view){
        currentIndex = (currentIndex + 1) % vprasanjeBank.length;
        vprasanje.setText(vprasanjeBank[currentIndex].getVprasanje());
        odgovor.setText(vprasanjeBank[currentIndex].getOdgovor());

        kateroVprasanjeOdKolkih.setText(String.valueOf(currentIndex) + "/" + String.valueOf(vprasanjeBank.length));
    }
}