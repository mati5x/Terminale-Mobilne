package com.mu.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewFilmActivity extends AppCompatActivity {

    private EditText mDirectorEditTxt;
    private EditText mTitleEditTxt;
    private EditText mReleaseDateEditTxt;
    private EditText mCategoryEditTxt;
    private Button mAdd_btn;
    private Button mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_film);

        mTitleEditTxt = (EditText) findViewById(R.id.titleEditTxt);
        mDirectorEditTxt = (EditText) findViewById(R.id.directorEditTxt);
        mReleaseDateEditTxt = (EditText) findViewById(R.id.releaseDateEditTxt);
        mCategoryEditTxt = (EditText) findViewById(R.id.categoryEditTxt);

        mAdd_btn=(Button) findViewById(R.id.add_btn);
        mBack_btn=(Button) findViewById(R.id.back_btn);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Film film = new Film();

                film.setTitle(mTitleEditTxt.getText().toString());
                film.setDirector(mDirectorEditTxt.getText().toString());
                film.setReleaseDate(mReleaseDateEditTxt.getText().toString());
                film.setCategory(mCategoryEditTxt.getText().toString());

                new FirebaseDataHelper().addFilm(film, NewFilmActivity.this);

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