package com.prakashmeghani.moviescraze;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prakashmeghani.moviescraze.Util.Constant;
import com.prakashmeghani.moviescraze.Util.NetworkUtils;
import com.prakashmeghani.moviescraze.Util.OpenMovieJsonUtils;
import com.prakashmeghani.moviescraze.Util.PrefManager;
import com.prakashmeghani.moviescraze.adapter.MovieAdapter;
import com.prakashmeghani.moviescraze.asynctask.FetchMovieTask;
import com.prakashmeghani.moviescraze.model.AsyncTaskCompleteListener;
import com.prakashmeghani.moviescraze.model.Movie;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter movieAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObjects();

        mRecyclerView = findViewById(R.id.recyclerview_forecast);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);

        GridLayoutManager gridLayoutManager
                = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(MainActivity.this, this);

        mRecyclerView.setAdapter(movieAdapter);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        if(NetworkUtils.isInternetWorking(MainActivity.this)){
            loadMovieData(prefManager.getUrlType());
        }else {
            showErrorMessage(getString(R.string.internet_not_working));
        }

    }

    private void initObjects() {
        prefManager = new PrefManager(MainActivity.this);
    }

    private void loadMovieData(int urlType) {
        showMovieDataView();

        mLoadingIndicator.setVisibility(View.VISIBLE);

        new FetchMovieTask(MainActivity.this, new FetchMovieCompleteListener()).execute(urlType);
    }

    private void showMovieDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage(String msg) {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        if (msg != null) {
            mErrorMessageDisplay.setText(msg);
        }
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
        detailIntent.putExtra(Constant.EXTRA_MOVIE, movie);
        startActivity(detailIntent);

    }

    public class FetchMovieCompleteListener implements AsyncTaskCompleteListener<ArrayList<Movie>>{

        @Override
        public void onTaskComplete(ArrayList<Movie> result) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (result != null) {
                if (result.get(0).getStatusMessage() != null) {
                    showErrorMessage(result.get(0).getStatusMessage());
                } else {
                    showMovieDataView();
                    movieAdapter.setMovieData(result);
                }
            } else {
                showErrorMessage(null);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            movieAdapter.setMovieData(null);
            loadMovieData(prefManager.getUrlType());
            return true;
        }

        if (id == R.id.action_popular) {
            prefManager.setUrlType(Constant.URL_TYPE_POPULAR);
            movieAdapter.setMovieData(null);
            loadMovieData(prefManager.getUrlType());
            return true;
        }

        if (id == R.id.action_top_rated) {
            prefManager.setUrlType(Constant.URL_TYPE_TOP_RATED);
            movieAdapter.setMovieData(null);
            loadMovieData(prefManager.getUrlType());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
