package si.uni_lj.fri.pbd.mojkvizko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MojiKviziActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    private RecyclerView courseRV;

    // Arraylist for storing data
    private ArrayList<CourseModel> courseModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moji_kvizi);

        courseRV = findViewById(R.id.idRVCourse);

        // here we have created new array list and added data to it.
        courseModelArrayList = new ArrayList<>();
        courseModelArrayList.add(new CourseModel("||. svetovna vojna", "Spraševanje", R.drawable.quizimage));
        courseModelArrayList.add(new CourseModel("Zgodovina", "Ucenje za prvi test", R.drawable.quizimage));
        courseModelArrayList.add(new CourseModel("Prazgodovina", "Ponovitev", R.drawable.quizimage));
        courseModelArrayList.add(new CourseModel("Stari vek", "Vaja", R.drawable.quizimage));
        courseModelArrayList.add(new CourseModel("Srednji vek", "Domaca naloga", R.drawable.quizimage));
        courseModelArrayList.add(new CourseModel("Novi vek", "Seminarska naloga", R.drawable.quizimage));
        courseModelArrayList.add(new CourseModel("Hladna vojna", "Vaja", R.drawable.quizimage));

        // we are initializing our adapter class and passing our arraylist to it.
        CourseAdapter courseAdapter = new CourseAdapter(this, courseModelArrayList, this);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

    }


    @Override
    public void onItemClick(int position) {
        courseModelArrayList.get(position);
        Intent intent = new Intent(this, IzbranKvizActivity.class);
        intent.putExtra("EXTRA_IME_KVIZA", courseModelArrayList.get(position).getCourse_name());
        startActivity(intent);
    }
}