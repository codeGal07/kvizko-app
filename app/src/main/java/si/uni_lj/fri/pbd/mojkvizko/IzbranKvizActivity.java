package si.uni_lj.fri.pbd.mojkvizko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IzbranKvizActivity extends AppCompatActivity {

    TextView imeKviza;
    private TextView kateroVprasanjeOdKolkih;
    private TextView vprasanje;
    private TextView odgovor;



    private VprasanjeModel[] vprasanjeBank = new VprasanjeModel[100];
    private int currentIndex =0;
    private boolean isVprasanje = true;
    int indeksZaVprasanjaBank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbran_kviz);


        String ime_kviza = getIntent().getStringExtra("EXTRA_IME_KVIZA");
        String ime_uporabnik = getIntent().getStringExtra("EXTRA_USER");
        imeKviza = (TextView) findViewById(R.id.ImeKviza);
        imeKviza.setText(ime_kviza);
        indeksZaVprasanjaBank = 0;



        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference applicationsRef = rootRef.collection("user");
        DocumentReference applicationIdRef = applicationsRef.document(ime_uporabnik);
        applicationIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                if (document.exists()) {
                    List<HashMap<String,  List<HashMap<String, String>>>> mojiKvizi = (List<HashMap<String,  List<HashMap<String, String>>>>) document.get("Kvizi");

                    for (Map<String,  List<HashMap<String, String>>> mojKviz : mojiKvizi) {

                        if (mojKviz.containsValue(ime_kviza)) {
                            Log.d("TAG", "------------MOJLISTMAP--------------------- " + mojKviz.get("vprasanjeModelList"));
                            for (HashMap<String, String> vprasanjeModelHash  : mojKviz.get("vprasanjeModelList")) {
                                vprasanjeBank[indeksZaVprasanjaBank] = new VprasanjeModel(vprasanjeModelHash.get("vprasanje"), vprasanjeModelHash.get("odgovor"));
                                indeksZaVprasanjaBank++;
                            }
                        }

                    }
                    vprasanje = (TextView) findViewById(R.id.trenutnoVprasanje);
                    vprasanje.setText(vprasanjeBank[currentIndex].getVprasanje());

                    odgovor = (TextView) findViewById(R.id.trenutenOdgovor);
                    odgovor.setText(vprasanjeBank[currentIndex].getOdgovor());

                    kateroVprasanjeOdKolkih = (TextView) findViewById(R.id.kateroOdKolkih);
                    kateroVprasanjeOdKolkih.setText(String.valueOf(currentIndex) + "/" + String.valueOf(indeksZaVprasanjaBank));


                }
            }
        });


//        if (ime_kviza.equals("||. svetovna vojna")) {
//            vprasanjeBank = new VprasanjeModel[]{
//                    new VprasanjeModel("V kateri državi se je zgodil dan D?", "V Franciji"),
//                    new VprasanjeModel("Kdo je bil zaveznik nacistične Nemčije?", "Italija in Japonska"),
//                    new VprasanjeModel("Katerega leta se je začela 2. svetovna vojna?", "1939"),
//            };
//        }
//
//        if (ime_kviza.equals("Prazgodovina")) {
//            vprasanjeBank = new VprasanjeModel[]{
//                    new VprasanjeModel("Kdo je avstrolopitek?", "Prvi človek al neki"),
//                    new VprasanjeModel("Kdaj je bla kamena doma?", "Enkrat v prazgodovini"),
//                    new VprasanjeModel("Kje so našli okostje avstrolopitka?", "V Afriki"),
//            };
//        }



    }

    public void updateVpasanje(View view){
        currentIndex = (currentIndex + 1) % indeksZaVprasanjaBank;
        vprasanje.setText(vprasanjeBank[currentIndex].getVprasanje());
        odgovor.setText(vprasanjeBank[currentIndex].getOdgovor());

        kateroVprasanjeOdKolkih.setText(String.valueOf(currentIndex) + "/" + String.valueOf(indeksZaVprasanjaBank));
    }
}