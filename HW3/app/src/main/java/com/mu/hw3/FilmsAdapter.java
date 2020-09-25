package com.mu.hw3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {

    private Context mCtx;
    private List<Film> filmList;


    public FilmsAdapter(Context mCtx, List<Film> filmList) {
        this.mCtx = mCtx;
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.fragment_film, parent, false)
        );
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        final Film film = filmList.get(position);
        final Context context = holder.mdeleteButton.getContext();
        holder.titleView.setText(film.getTitle());
        holder.directorView.setText(film.getDirector());
        holder.releaseDateView.setText(film.getReleaseDate());
        holder.categoryView.setText(film.getCategory());

        holder.mdeleteButton.setOnClickListener(    new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage(R.string.delete_question)
                        .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseFirestore db;
                                db = FirebaseFirestore.getInstance();
                                db.collection("films").document(film.getId()).delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(context, "Pozyzja została usunięta", Toast.LENGTH_LONG).show();
                                                    notifyDataSetChanged();
                                                }
                                            }
                                        });
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, null)
                        .show();
            }
        });

    }


    class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleView;
        TextView directorView;
        TextView releaseDateView;
        TextView categoryView;
        ImageButton mdeleteButton;

        public FilmViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.titleView);
            directorView = itemView.findViewById(R.id.directorView);
            releaseDateView = itemView.findViewById(R.id.releaseDateView);
            categoryView = itemView.findViewById(R.id.categoryView);
            mdeleteButton = itemView.findViewById(R.id.deleteButton);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Film film = filmList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateFilmActivity.class);
            intent.putExtra("film", film);
            mCtx.startActivity(intent);
        }
    }
}

