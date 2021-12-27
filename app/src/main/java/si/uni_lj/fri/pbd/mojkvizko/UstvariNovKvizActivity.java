package si.uni_lj.fri.pbd.mojkvizko;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UstvariNovKvizActivity extends AppCompatActivity {

    private EditText imeKvizaInput;
    private EditText vprasanjeInput;
    private EditText odgovorInput;
    private boolean novKviz;
    private VprasanjeModel dodanoVprasanje;
    ArrayList<KvizModel> kvizModelList_u2 = new ArrayList<KvizModel>();
    private String uporabnisko_ime;
    FirebaseFirestore db;
    String ime_kviza;
    ArrayList<VprasanjeModel> vprasanjaList1_u2 = new ArrayList<VprasanjeModel>();
    //    private VprasanjeModel[] vprasanjeBank = new VprasanjeModel[5];
    private ArrayList<VprasanjeModel> vprasanjaListIzBaze = new ArrayList<VprasanjeModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustvari_nov_kviz);


        uporabnisko_ime = getIntent().getStringExtra("EXTRA_UPORABNISKO_IME");
        imeKvizaInput = findViewById(R.id.imeKvizaInput);
        vprasanjeInput = findViewById(R.id.vprasanjeInput);
        odgovorInput = findViewById(R.id.odgovorInput);

        ime_kviza = imeKvizaInput.getText().toString();

        dodanoVprasanje = new VprasanjeModel(vprasanjeInput.getText().toString(), odgovorInput.getText().toString());

        db = FirebaseFirestore.getInstance();


        // v on create morm napolnit
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference applicationsRef = rootRef.collection("user");
        DocumentReference applicationIdRef = applicationsRef.document(uporabnisko_ime);
        applicationIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                if (document.exists()) {
                    List<String> naslovi = new ArrayList<>();
                    int katernaslov = 0;
                    List<HashMap<String, String>> mojiKvizi_samoZaNaslov = (List<HashMap<String, String>>) document.get("Kvizi");

//                    Log.d("TAG", "------------MOJMAP--------------------- " + mojiKvizi.get(0));
                    for (Map<String, String> mojKviz : mojiKvizi_samoZaNaslov) {
                        naslovi.add(mojKviz.get("imeKviza"));
                    }
                    List<HashMap<String, List<HashMap<String, String>>>> mojiKvizi = (List<HashMap<String, List<HashMap<String, String>>>>) document.get("Kvizi");

                    for (Map<String, List<HashMap<String, String>>> mojKviz : mojiKvizi) {
                        vprasanjaList1_u2.clear();
                        for (HashMap<String, String> vprasanjeModelHash : mojKviz.get("vprasanjeModelList")) {
                            vprasanjaList1_u2.add(new VprasanjeModel(vprasanjeModelHash.get("vprasanje"), vprasanjeModelHash.get("odgovor")));
                            Log.d("TAG", "---------IME KVIZAAAAAAAAAAAAAA-----------: " + naslovi.get(katernaslov));
                        }
                        KvizModel kvizModel_u2 = new KvizModel(naslovi.get(katernaslov), uporabnisko_ime, vprasanjaList1_u2);
                        kvizModelList_u2.add(kvizModel_u2);
                        katernaslov++;

                    }

                }
            }
        });


    }

    public void dodajVprasanje(View view) {

        imeKvizaInput = findViewById(R.id.imeKvizaInput);
        vprasanjeInput = findViewById(R.id.vprasanjeInput);
        odgovorInput = findViewById(R.id.odgovorInput);


        novKviz = true;

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference applicationsRef = rootRef.collection("user");
        DocumentReference applicationIdRef = applicationsRef.document(uporabnisko_ime);
        applicationIdRef.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                Log.d("TAG", "---------DocumentSnapshot dataaaaa-----------: " + document.getData());

                if (document.exists()) {
                    List<String> naslovi = new ArrayList<>();
                    int katernaslov = 0;
                    List<HashMap<String, List<HashMap<String, String>>>> mojiKvizi = (List<HashMap<String, List<HashMap<String, String>>>>) document.get("Kvizi");
                    List<HashMap<String, String>> mojiKvizi_samoZaNaslov = (List<HashMap<String, String>>) document.get("Kvizi");

//                    Log.d("TAG", "------------MOJMAP--------------------- " + mojiKvizi.get(0));
                    for (Map<String, String> mojKviz : mojiKvizi_samoZaNaslov) {
                        naslovi.add(mojKviz.get("imeKviza"));
                    }
                    Log.d("TAG", "---------IME KVIZAAAAAAAAAAAAAA-----------: " + naslovi);


                    //samo zato da dodam notr vprašanje v obstoječega če obstaja in ga shranim v vprasanjaList1_u2
                    for (Map<String, List<HashMap<String, String>>> mojKviz : mojiKvizi) {
//                        Log.d("TAG", "---------wwwwwwwwwwwwwwww-----------: " + mojKviz.get("imeKviza"));

                        if (mojKviz.containsValue(imeKvizaInput.getText().toString())) {

                            //najdem kviz k ma to ime in ga deletam iz kvizModelList_u2
                            for (KvizModel obj : kvizModelList_u2) {
                                if (obj.getImeKviza().equals(imeKvizaInput.getText().toString())) {
                                    kvizModelList_u2.remove(obj);
                                }
                            }

                            novKviz = false;
                            vprasanjaList1_u2.clear();
                            for (HashMap<String, String> vprasanjeModelHash : mojKviz.get("vprasanjeModelList")) {
                                vprasanjaList1_u2.add(new VprasanjeModel(vprasanjeModelHash.get("vprasanje"), vprasanjeModelHash.get("odgovor")));
                            }
                            vprasanjaList1_u2.add(new VprasanjeModel(vprasanjeInput.getText().toString(), odgovorInput.getText().toString()));
                            KvizModel kvizModel_u2 = new KvizModel(imeKvizaInput.getText().toString(), uporabnisko_ime, vprasanjaList1_u2);
                            kvizModelList_u2.add(kvizModel_u2);
                        }

                    }

                    if (novKviz) {
                        vprasanjaList1_u2.clear();
                        vprasanjaList1_u2.add(new VprasanjeModel(vprasanjeInput.getText().toString(), odgovorInput.getText().toString()));
                        KvizModel kvizModel_u2 = new KvizModel(imeKvizaInput.getText().toString(), uporabnisko_ime, vprasanjaList1_u2);
                        kvizModelList_u2.add(kvizModel_u2);
                    }
                    vprasanjeInput.setText("");
                    odgovorInput.setText("");

                    Map<String, Object> user_u2 = new HashMap<>();
                    user_u2.put("Kvizi", kvizModelList_u2);

                    db.collection("user").document(uporabnisko_ime)
                            .set(user_u2)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error writing document", e);
                                }
                            });


                }
            }
        });


        Toast.makeText(this, "Vprašanje uspešno dodano", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, BasicPageActvity.class);
//        intent.putExtra("EXTRA_UPORABNISKO_IME", mail.getText().toString());
//        startActivity(intent);


//        vprasanjeInput.setText("");
//        odgovorInput.setText("");

    }

//    public void dodajVprasanjeFun (String uporabnisko_ime) {
//        ArrayList<KvizModel> kvizModelList = new ArrayList<KvizModel>();
//        //SECOND USER
//        vprasanjaListIzBaze = pridobiVprasanja(uporabnisko_ime, imeKvizaInput.getText().toString());
//        vprasanjaListIzBaze.add( new VprasanjeModel(vprasanjeInput.getText().toString(), odgovorInput.getText().toString()));
//        KvizModel kvizModel_u2 = new KvizModel(imeKvizaInput.toString(), uporabnisko_ime, vprasanjaListIzBaze);
//
//    }

//    //todo to ne dela
//    public ArrayList<VprasanjeModel> pridobiVprasanja(String ime_uporabnik, String ime_kviza) {
//        ArrayList<VprasanjeModel> vprasanjaList= new ArrayList<VprasanjeModel>();
//        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
//        CollectionReference applicationsRef = rootRef.collection("user");
//        DocumentReference applicationIdRef = applicationsRef.document(ime_uporabnik);
//        Log.d("TAG", "------------wwwwwwwwwwwwwwwwwwwwwwwww--------------------- ");
//        applicationIdRef.get().addOnCompleteListener(task -> {
//            Log.d("TAG", "------------dddlčkjčkfAna--------------------- ");
//            if (task.isSuccessful()) {
//                DocumentSnapshot document = task.getResult();
//                Log.d("TAG", "------------eeeeeeeeeeeeeeeeeeeeeeeeee--------------------- ");
//                Log.d("TAG", "DocumentSnapshot data: " + document.getData());
//                if (document.exists()) {
//                    Log.d("TAG", "------------fffffffffffffffffffffffffffffff--------------------- ");
//                    List<HashMap<String, List<HashMap<String, String>>>> mojiKvizi = (List<HashMap<String,  List<HashMap<String, String>>>>) document.get("Kvizi");
//                    Log.d("TAG", "------------bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb--------------------- ");
//                    for (Map<String,  List<HashMap<String, String>>> mojKviz : mojiKvizi) {
//                        Log.d("TAG", "------------aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa--------------------- " + mojKviz.get("vprasanjeModelList"));
//                        if (mojKviz.containsValue(ime_kviza)) {
//                            Log.d("TAG", "------------MOJLISTMAP--------------------- " + mojKviz.get("vprasanjeModelList"));
//                            for (HashMap<String, String> vprasanjeModelHash  : mojKviz.get("vprasanjeModelList")) {
//                                vprasanjaList.add(new VprasanjeModel(vprasanjeModelHash.get("vprasanje"), vprasanjeModelHash.get("odgovor"))) ;
//                            }
//
//                        }
//
//                    }
//
//                }
//            }
//        });
//      return vprasanjaList;
//    }
}