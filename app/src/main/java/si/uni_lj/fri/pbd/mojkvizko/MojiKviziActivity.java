package si.uni_lj.fri.pbd.mojkvizko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MojiKviziActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    private RecyclerView courseRV;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Arraylist for storing data
    private ArrayList<CourseModel> courseModelArrayList;
    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moji_kvizi);

        String uporabnisko_ime = getIntent().getStringExtra("EXTRA_UPORABNISKO_IME");

        label = (TextView) findViewById(R.id.kvizLabel);
        if (uporabnisko_ime.equals("VSI_KVIZI")) {
            label.setText("Vsi kvizi");
        } else {
            label.setText("Moji kvizi");
        }


        courseRV = findViewById(R.id.idRVCourse);

        courseModelArrayList = new ArrayList<>();
        CourseAdapter courseAdapter = new CourseAdapter(this, courseModelArrayList, this);

        db = FirebaseFirestore.getInstance();


        if (uporabnisko_ime.equals("VSI_KVIZI")) {

            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
            CollectionReference applicationsRef = rootRef.collection("user");
            //todo temp string Janez123, change to uporabnisko_ime
            DocumentReference applicationIdRef = applicationsRef.document("Janez123");
            applicationIdRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                    if (document.exists()) {
                        List<HashMap<String, String>> mojiKvizi = (List<HashMap<String, String>>) document.get("Kvizi");

//                    Log.d("TAG", "------------MOJMAP--------------------- " + mojiKvizi.get(0));
                        for (Map<String, String> mojKviz : mojiKvizi) {

                            System.out.println(mojKviz);
                            courseModelArrayList.add(new CourseModel(mojKviz.get("imeKviza"), "aa", R.drawable.quizimage, "Janez123"));
//                        list.add(mojKviz.get("imeKviza").getImeKviza());
                            Log.d("TAG", "------------MOJLISTMAP--------------------- " + mojKviz.get("imeKviza"));
                        }
                        courseAdapter.notifyDataSetChanged();


                    }
                }
            });

            applicationIdRef = applicationsRef.document("Ana123");
            applicationIdRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                    if (document.exists()) {
                        List<HashMap<String, String>> mojiKvizi = (List<HashMap<String, String>>) document.get("Kvizi");

//                    Log.d("TAG", "------------MOJMAP--------------------- " + mojiKvizi.get(0));
                        for (Map<String, String> mojKviz : mojiKvizi) {

                            System.out.println(mojKviz.get("imeKviza"));
                            courseModelArrayList.add(new CourseModel(mojKviz.get("imeKviza"), "aa", R.drawable.quizimage, "Ana123"));
//                        list.add(mojKviz.get("imeKviza").getImeKviza());
                            Log.d("TAG", "------------MOJLISTMAP--------------------- " + mojKviz.get("imeKviza"));
                        }
                        courseAdapter.notifyDataSetChanged();


                    }
                }
            });
        } else {

            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
            CollectionReference applicationsRef = rootRef.collection("user");
            //todo temp string Janez123, change to uporabnisko_ime
            DocumentReference applicationIdRef = applicationsRef.document(uporabnisko_ime);
            applicationIdRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                    if (document.exists()) {
                        List<HashMap<String, String>> mojiKvizi = (List<HashMap<String, String>>) document.get("Kvizi");

//                    Log.d("TAG", "------------MOJMAP--------------------- " + mojiKvizi.get(0));
                        for (Map<String, String> mojKviz : mojiKvizi) {

                            System.out.println(mojKviz.get("imeKviza"));
                            courseModelArrayList.add(new CourseModel(mojKviz.get("imeKviza"), "aa", R.drawable.quizimage, uporabnisko_ime));
//                        list.add(mojKviz.get("imeKviza").getImeKviza());
                            Log.d("TAG", "------------MOJLISTMAP--------------------- " + mojKviz.get("imeKviza"));
                        }
                        courseAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

    }

    @Override
    public void onItemClick(int position) {
        courseModelArrayList.get(position);
        Intent intent = new Intent(this, IzbranKvizActivity.class);
        intent.putExtra("EXTRA_IME_KVIZA", courseModelArrayList.get(position).getCourse_name());
        intent.putExtra("EXTRA_USER", courseModelArrayList.get(position).getUporabnik());

        startActivity(intent);
    }

}