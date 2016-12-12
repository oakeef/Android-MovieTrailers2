package com.example.evan.movieapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private DbHelper databaseHelper;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        databaseHelper = new DbHelper(this);
        movieList = new ArrayList<>();
        reloadingDatabase(); //loading table of DB to ListView
    }

    public void reloadingDatabase() {
        movieList = databaseHelper.getAllMovies();
        if (movieList.size() == 0) {
            Toast.makeText(this, "No record found in database!", Toast.LENGTH_SHORT).show();
        }
        adapter = new ListViewAdapter(this, R.layout.item_listview, movieList, databaseHelper);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            addingNewMovieDialog();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addingNewMovieDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Add a new Movie");

        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(10, 10, 10, 10);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(this);
        titleBox.setHint("Movie Title");
        layout.addView(titleBox);

        final EditText descBox = new EditText(this);
        descBox.setHint("Description");
        layout.addView(descBox);

        final EditText thumbBox = new EditText(this);
        thumbBox.setHint("Thumbnail URL");
        layout.addView(thumbBox);

        final EditText videoBox = new EditText(this);
        videoBox.setHint("Youtube URL");
        layout.addView(videoBox);

        final EditText ratingBox = new EditText(this);
        ratingBox.setHint("Rate Video out of 5");
        layout.addView(ratingBox);

        alertDialog.setView(layout);

        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Movie movie = new Movie(titleBox.getText().toString(),
                        descBox.getText().toString(),
                        thumbBox.getText().toString(),
                        videoBox.getText().toString(),
                        Integer.parseInt(ratingBox.getText().toString())
                );
                databaseHelper.addNewMovie(movie);

                reloadingDatabase(); //reload the db to view
            }
        });

        alertDialog.setNegativeButton("Cancel", null);

        //show alert
        alertDialog.show();
    }

    //get text available in TextView/EditText
    private String getText(TextView textView) {
        return textView.getText().toString().trim();
    }
}