package com.mu.hw3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=(RecyclerView)findViewById(R.id.filmFragment);
    }

    public void clickAdd(View view) {
        Intent intent = new Intent(this, NewFilmActivity.class);
        startActivity(intent);
    }

    public void clickShow(View view) {
        RecyclerView recyclerView;
        FirebaseFirestore db;
        final List<Film> filmList;
        final FilmsAdapter adapter;

        db = FirebaseFirestore.getInstance();
        filmList = new ArrayList<>();
        adapter = new FilmsAdapter(this, filmList);
        recyclerView = findViewById(R.id.filmFragment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        db.collection("films").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            Toast.makeText(MainActivity.this, "Wyświetlono zawartość bazy", Toast.LENGTH_LONG).show();

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : list){

                                Film p = d.toObject(Film.class);
                                assert p != null;
                                p.setId(d.getId());
                                filmList.add(p);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
