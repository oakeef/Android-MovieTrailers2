package com.example.evan.movieapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Evan on 12/11/2016.
 */

public class ListViewAdapter extends ArrayAdapter<Movie> {

    private MainActivity activity;
    private DbHelper databaseHelper;
    private List<Movie> movieList;

    public ListViewAdapter(MainActivity context, int resource, List<Movie> objects, DbHelper helper) {
        super(context, resource, objects);
        this.activity = context;
        this.databaseHelper = helper;
        this.movieList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(getItem(position).getTitle());

        //Delete an item
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteMovie(getItem(position)); //delete in db
                Toast.makeText(activity, "Deleted!", Toast.LENGTH_SHORT).show();

                //reload the database to view
                activity.reloadingDatabase();
            }
        });

        //Edit/Update an item
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle("Update a Movie");

                LinearLayout layout = new LinearLayout(activity);
                layout.setPadding(10, 10, 10, 10);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText titleBox = new EditText(activity);
                titleBox.setHint("Movie Title");
                layout.addView(titleBox);

                final EditText descBox = new EditText(activity);
                descBox.setHint("Description");
                layout.addView(descBox);

                final EditText thumbBox = new EditText(activity);
                thumbBox.setHint("Thumbnail URL");
                layout.addView(thumbBox);

                final EditText videoBox = new EditText(activity);
                videoBox.setHint("Youtube URL");
                layout.addView(videoBox);

                final EditText ratingBox = new EditText(activity);
                ratingBox.setHint("Rate Video out of 5");
                layout.addView(ratingBox);

                titleBox.setText(getItem(position).getTitle());
                descBox.setText(getItem(position).getDescription());
                thumbBox.setText(getItem(position).getThumbnail());
                videoBox.setText(getItem(position).getVideo());
                ratingBox.setText(Integer.toString(getItem(position).getRating()));

                alertDialog.setView(layout);

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Movie movie = new Movie(
                                titleBox.getText().toString(),
                                descBox.getText().toString(),
                                thumbBox.getText().toString(),
                                videoBox.getText().toString(),
                                Integer.parseInt(ratingBox.getText().toString())
                                                );
                        movie.setId(getItem(position).getId());
                        databaseHelper.updateMovie(movie); //update to db
                        Toast.makeText(activity, "Updated!", Toast.LENGTH_SHORT).show();

                        //reload the database to view
                        activity.reloadingDatabase();
                    }
                });

                alertDialog.setNegativeButton("Cancel", null);

                //show alert dialog
                alertDialog.show();
            }
        });

        //show details when each row item clicked
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle("Movie ");

                LinearLayout layout = new LinearLayout(activity);
                layout.setPadding(10, 10, 10, 10);
                layout.setOrientation(LinearLayout.VERTICAL);

                ImageView poster = new ImageView(activity);
                layout.addView(poster);

                TextView titleBox = new TextView(activity);
                layout.addView(titleBox);

                TextView descBox = new TextView(activity);
                layout.addView(descBox);

                TextView videoBox = new TextView(activity);
                layout.addView(videoBox);

                TextView ratingBox = new TextView(activity);
                layout.addView(ratingBox);

                Movie movie = getItem(position);

                String imgURL = movie.getThumbnail();

                Picasso.with(activity).load(imgURL).into(poster);

                titleBox.setText("Movie Title: " + movie.getTitle());
                descBox.setText("Description: " + movie.getDescription());
                videoBox.setText("Video URL: " + movie.getVideo());
                ratingBox.setText("Rating: " + movie.getRating() + "/5");

                alertDialog.setView(layout);
                alertDialog.setNegativeButton("OK", null);

                //show alert
                alertDialog.show();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        private TextView title;
        private View btnDelete;
        private View btnEdit;

        public ViewHolder (View v) {
            title = (TextView)v.findViewById(R.id.item_title);
            btnDelete = v.findViewById(R.id.delete);
            btnEdit = v.findViewById(R.id.edit);
        }
    }

}