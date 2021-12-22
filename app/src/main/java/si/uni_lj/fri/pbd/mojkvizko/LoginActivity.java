package si.uni_lj.fri.pbd.mojkvizko;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    EditText mail;
    EditText password;
    Button btn;
    FirebaseFirestore db;

    ArrayList<VprasanjeModel> vprasanjaList1_u1 = new ArrayList<VprasanjeModel>();
    ArrayList<VprasanjeModel> vprasanjaList2_u1 = new ArrayList<VprasanjeModel>();
    ArrayList<KvizModel> kvizModelList_u1 = new ArrayList<KvizModel>();

    ArrayList<VprasanjeModel> vprasanjaList1_u2 = new ArrayList<VprasanjeModel>();
    ArrayList<VprasanjeModel> vprasanjaList2_u2 = new ArrayList<VprasanjeModel>();
    ArrayList<KvizModel> kvizModelList_u2 = new ArrayList<KvizModel>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.login);

        db = FirebaseFirestore.getInstance();

//        btn.setOnClickListener(v ->
//        {
//            Map<String, Object> user = new HashMap<>();
//            user.put("Email", mail.getText().toString());
//            user.put("Geslo", password.getText().toString());
//
//
////            Uporabnik uporabnik = new Uporabnik(mail.getText().toString(), password.getText().toString());
//            db.collection("user")
//                    .add(user)
//                    .addOnSuccessListener(suc ->
//            {
//                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er ->
//            {
//                Toast.makeText(this, "ne dela", Toast.LENGTH_SHORT).show();
//            });
//        });


        //run to add user and quiz to database
        // FIRST USER
        vprasanjaList1_u1.add( new VprasanjeModel("V kateri državi se je zgodil dan D?", "V Franciji"));
        vprasanjaList1_u1.add( new VprasanjeModel("Kdo je bil zaveznik nacistične Nemčije?", "Italija in Japonska"));
        vprasanjaList1_u1.add( new VprasanjeModel("Katerega leta se je začela 2. svetovna vojna?", "1939"));
        KvizModel kvizModel1_u1 = new KvizModel("||. svetovna vojna", vprasanjaList1_u1);

        vprasanjaList2_u1.add( new VprasanjeModel("Kdo je avstrolopitek?", "Prvi človek al neki"));
        vprasanjaList2_u1.add( new VprasanjeModel("Kdaj je bla kamena doma?", "Enkrat v prazgodovini"));
        vprasanjaList2_u1.add( new VprasanjeModel("Kje so našli okostje avstrolopitka?", "V Afriki"));
        KvizModel kvizModel2_u1 = new KvizModel("Prazgodovina", vprasanjaList2_u1);

        kvizModelList_u1.add(kvizModel1_u1);
        kvizModelList_u1.add(kvizModel2_u1);

        Map<String, Object> user_u1 = new HashMap<>();
        user_u1.put("Kvizi", kvizModelList_u1);

        db.collection("user").document("Janez123")
                .set(user_u1)
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

        //SECOND USER
        vprasanjaList1_u2.add( new VprasanjeModel("Kdaj je obstajalo rimsko cesarstvo", "27 pr. n. št.–1453"));
        vprasanjaList1_u2.add( new VprasanjeModel("Kater jezik so govorili rimljani", "Latinsko"));
        KvizModel kvizModel_u2 = new KvizModel("Rimljani", vprasanjaList1_u2);

        kvizModelList_u2.add(kvizModel_u2);

        Map<String, Object> user_u2 = new HashMap<>();
        user_u2.put("Kvizi", kvizModelList_u2);

        db.collection("user").document("Ana123")
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


    public void loginUser(View view) {

        mail = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.login);


        Intent intent = new Intent(this, BasicPageActvity.class);
        intent.putExtra("EXTRA_UPORABNISKO_IME", mail.getText().toString());
        startActivity(intent);

    }
}
