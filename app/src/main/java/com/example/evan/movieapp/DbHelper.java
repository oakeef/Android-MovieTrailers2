package com.example.evan.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 12/11/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieDB";

    // Movie table name
    private static final String TABLE_MOVIE = "movie";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_THUMBNAIL = "thumbnail";
    private static final String KEY_VIDEO = "video";
    private static final String KEY_RATING = "rating";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIE_TABLE =
                "CREATE TABLE " + TABLE_MOVIE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_THUMBNAIL + " TEXT,"
                + KEY_VIDEO + " TEXT," + KEY_RATING + " INT" + ")";
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);

        // Create tables again
        onCreate(db);
    }

    public void addNewMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_DESCRIPTION, movie.getDescription());
        values.put(KEY_THUMBNAIL, movie.getThumbnail());
        values.put(KEY_VIDEO, movie.getVideo());
        values.put(KEY_RATING, movie.getRating());

        // inserting this record
        db.insert(TABLE_MOVIE, null, values);
        db.close(); // Closing database connection
    }

    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIE, KEY_ID + " = ?", new String[]{String.valueOf(movie.getId())});
        db.close();
    }

    public int updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_DESCRIPTION, movie.getDescription());
        values.put(KEY_THUMBNAIL, movie.getThumbnail());
        values.put(KEY_VIDEO, movie.getVideo());

        // updating row
        return db.update(TABLE_MOVIE, values, KEY_ID + " = ?", new String[]{String.valueOf(movie.getId())});
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();

        // select query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all table records and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();

                movie.setId(Integer.parseInt(cursor.getString(0)));
                movie.setTitle(cursor.getString(1));
                movie.setDescription(cursor.getString(2));
                movie.setThumbnail(cursor.getString(3));
                movie.setVideo(cursor.getString(4));
                movie.setRating(Integer.parseInt(cursor.getString(5)));

                // Adding Movie to list
                movieList.add(movie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return movieList;
    }
}
