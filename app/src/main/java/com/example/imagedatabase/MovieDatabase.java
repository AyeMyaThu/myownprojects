package com.example.imagedatabase;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDatabase extends SQLiteOpenHelper {

	SQLiteDatabase db;

	public MovieDatabase(Context context) {
		super(context, "movie.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE tbl_movie(id CHAR primary key, title CHAR, starring CHAR, category CHAR, rating CHAR, image CHAR)";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void addMovie(Movie m) {

		db = getWritableDatabase();
		String sql = "INSERT INTO tbl_movie VALUES ('" + m.getId() + "','"
				+ m.getTitle() + "','" + m.getStarring() + "','"
				+ m.getCategory() + "','"
				+ m.getRate() + "','"+m.getImage()+"')";
		db.execSQL(sql);
		db.close();
	}

	public void deleteMovie(long selectedId) {

		db = getWritableDatabase();
		// String userselectedID = String.valueOf(selectedId);
		String sql = "DELETE FROM tbl_movie WHERE id =" + selectedId + "";
		db.execSQL(sql);
		db.close();
	}
	public void updateMovie(Movie m) {
		db = getWritableDatabase();
		String sql = "UPDATE tbl_movie SET title = '" + m.getTitle()
		+ "', starring = '" + m.getStarring() + "', category = '"
		+ m.getCategory() + "', rating = '" + m.getRate() + "', image='"+m.getImage()+"' where id = '"+m.getId()+"'";
		db.execSQL(sql);
		db.close();

		}
	
	public Movie getMoviebyID(long selectedId){
		
		Movie m = new Movie();
		
		db = getReadableDatabase();
		String sql = "SELECT * FROM tbl_movie where id = "+selectedId+"";
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.moveToFirst()){		
			
			m.setId(cursor.getString(0));
			m.setTitle(cursor.getString(1));
			m.setStarring(cursor.getString(2));
			m.setCategory(cursor.getString(3));
			m.setRate(Integer.parseInt(cursor.getString(4)));	
			m.setImage(cursor.getString(5));
				
		}
		
		return m;
		
	}

	public ArrayList<Movie> getAllMovie() {

		db = getReadableDatabase();
		ArrayList<Movie> list = new ArrayList<Movie>();

		String sql = "SELECT * FROM tbl_movie";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {

			do {

				Movie m = new Movie();

				m.setId(cursor.getString(0));
				m.setTitle(cursor.getString(1));
				m.setStarring(cursor.getString(2));
				m.setCategory(cursor.getString(3));

				m.setRate(Integer.parseInt(cursor.getString(4)));
				m.setImage(cursor.getString(5));
				list.add(m);
			} while (cursor.moveToNext());
		}

		db.close();
		return list;

	}

}
