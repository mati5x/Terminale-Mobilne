package com.mu.hw3;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDataHelper {

    private FirebaseFirestore db;
    CollectionReference dbFilmsRef;

    public FirebaseDataHelper() {
        db = FirebaseFirestore.getInstance();
        dbFilmsRef = db.collection("films");
    }

    public void addFilm(Film film, final Context context) {
        dbFilmsRef.add(film)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Film został dodany", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
                });
    }

    public void updateFilm(Film film, final Context context){
        dbFilmsRef.document(film.getId())
                .set(film)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Film zaktualizowany", Toast.LENGTH_LONG).show();
                    }
                });
    }
}