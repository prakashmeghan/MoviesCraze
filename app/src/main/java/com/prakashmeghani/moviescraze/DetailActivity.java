package com.prakashmeghani.moviescraze;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.prakashmeghani.moviescraze.Util.Constant;
import com.prakashmeghani.moviescraze.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Movie movie;
    private ImageView ivDetail;
    private TextView tvReleaseDate, tvOverview, tvTitle, tvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getDataFromIntent();

        findViewByIds();

        displayDetails();
    }

    private void displayDetails() {
        tvTitle.setText(movie.getTitle());
        tvRating.setText(getString(R.string.rating) + ": " + movie.getVoteAverage());
        tvOverview.setText(movie.getOverview());
        tvReleaseDate.setText(getString(R.string.release_date) + ": " + movie.getReleaseDate());

        String imgPath = Constant.URL_POSTER + movie.getPosterPath();
        Picasso.with(DetailActivity.this).load(imgPath).into(ivDetail);
    }

    private void findViewByIds() {
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvOverview = findViewById(R.id.tv_overview);
        tvRating = findViewById(R.id.tv_rating);
        tvTitle = findViewById(R.id.tv_title);
        ivDetail = findViewById(R.id.iv_movie_detail);
    }

    private void getDataFromIntent() {
        Intent i = getIntent();
        if (i != null) {
            movie = i.getParcelableExtra(Constant.EXTRA_MOVIE);
        }
    }
}
