package com.mu.hw3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateFilmActivity extends AppCompatActivity{

    private EditText mTitleEditTxt;
    private EditText mDirectorEditTxt;
    private EditText mReleaseDateEditTxt;
    private EditText mCategoryEditTxt;

    private Button mUpdate_btn;
    private Button mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_film);
        Film film;
        film = (Film) getIntent().getSerializableExtra("film");

        mTitleEditTxt = (EditText) findViewById(R.id.titleEditTxt);
        mDirectorEditTxt = (EditText) findViewById(R.id.directorEditTxt);
        mReleaseDateEditTxt = (EditText) findViewById(R.id.releaseDateEditTxt);
        mCategoryEditTxt = (EditText) findViewById(R.id.categoryEditTxt);

        mTitleEditTxt.setText(film.getTitle());
        mDirectorEditTxt.setText(film.getDirector());
        mReleaseDateEditTxt.setText(film.getReleaseDate());
        mCategoryEditTxt.setText(film.getCategory());

        mUpdate_btn=(Button) findViewById(R.id.update_btn);
        mBack_btn=(Button) findViewById(R.id.back_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String title = mTitleEditTxt.getText().toString().trim();
                String director = mDirectorEditTxt.getText().toString().trim();
                String releaseDate = mReleaseDateEditTxt.getText().toString().trim();
                String category = mCategoryEditTxt.getText().toString().trim();

                Film p = new Film(title, director, releaseDate, category);

                new FirebaseDataHelper().updateFilm(p, UpdateFilmActivity.this);

                mTitleEditTxt.setText("");
                mDirectorEditTxt.setText("");
                mReleaseDateEditTxt.setText("");
                mCategoryEditTxt.setText("");
            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }

}