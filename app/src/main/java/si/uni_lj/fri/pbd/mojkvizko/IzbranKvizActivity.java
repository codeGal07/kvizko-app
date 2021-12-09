package si.uni_lj.fri.pbd.mojkvizko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IzbranKvizActivity extends AppCompatActivity {

    TextView imeKviza;
    private TextView vprasanjeAliOdgovor;
    private TextView labelVprasanjeOdgovor;
    private TextView kateroVprasanjeOdKolkih;

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

        vprasanjeAliOdgovor = (TextView) findViewById(R.id.vprasanjeAliOdgovor);
        vprasanjeAliOdgovor.setText(vprasanjeBank[currentIndex].getVprasanje());

        labelVprasanjeOdgovor = (TextView) findViewById(R.id.labelVprasanjeOdgovor);
        labelVprasanjeOdgovor.setText("Vprasanje:");

        kateroVprasanjeOdKolkih = (TextView) findViewById(R.id.kateroOdKolkih);
        kateroVprasanjeOdKolkih.setText(String.valueOf(currentIndex) + "/" + String.valueOf(vprasanjeBank.length));

    }

    public void shiftOdgovorVprasanje(View view){
        if (isVprasanje) {


            String odgovor = vprasanjeBank[currentIndex].getOdgovor();
            vprasanjeAliOdgovor.setText(odgovor);
            labelVprasanjeOdgovor.setText("Odgovor:");
            isVprasanje=false;
        } else {
            String vprasanje = vprasanjeBank[currentIndex].getVprasanje();
            vprasanjeAliOdgovor.setText(vprasanje);
            labelVprasanjeOdgovor.setText("Vprasanje:");
            isVprasanje=true;
        }

    }

    public void updateVpasanje(View view){
        currentIndex = (currentIndex + 1) % vprasanjeBank.length;

        String vprasanje = vprasanjeBank[currentIndex].getVprasanje();
        vprasanjeAliOdgovor.setText(vprasanje);
        kateroVprasanjeOdKolkih.setText(String.valueOf(currentIndex) + "/" + String.valueOf(vprasanjeBank.length));
    }
}