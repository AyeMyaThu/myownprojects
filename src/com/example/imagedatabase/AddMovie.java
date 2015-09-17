package com.example.imagedatabase;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ToggleButton;

public class AddMovie extends Activity {
	
	MovieDatabase mdb;
	EditText title, starring,category;
	RatingBar rating;
	ImageView thumb;
	Uri imageURI=null;
	long itemid = 0;//add mode
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		mdb = new MovieDatabase(getApplicationContext());
	    title = (EditText) findViewById(R.id.atitle);
    	starring = (EditText) findViewById(R.id.astarring);
    	category = (EditText) findViewById(R.id.acategory);
    	rating = (RatingBar) findViewById(R.id.arate);   
    	thumb = (ImageView) findViewById(R.id.thumbnailImg);   	 
    	
    	if(getIntent().getExtras() != null){
    	
    			itemid= getIntent().getExtras().getLong("itemid");
    			Movie m= mdb.getMoviebyID(itemid);
    			title.setText(m.getTitle());
    			starring.setText(m.getStarring());
    			category.setText(m.getCategory());
    			rating.setRating(m.getRate());
    			thumb.setImageURI(Uri.parse(m.getImage()));    		
    			imageURI = Uri.parse(mdb.getMoviebyID(itemid).getImage());
    	}
		
	}

	
	
	public void save(View v){		
		
    	Movie m = new Movie();
    	if(itemid == 0)
    	{
    		m.setId(SystemClock.currentThreadTimeMillis()+"");
    		
    	}else{
    		
    		m.setId(itemid+"");
    	}
		//u.setId(id.getText().toString());
		
		m.setTitle(title.getText().toString());
		m.setStarring(starring.getText().toString());
		m.setCategory(category.getText().toString());
		m.setRate((int)rating.getRating());
			
		if (imageURI != null) {
				
			m.setImage(imageURI.toString());			
		} 			
		else 			
		{				
			m.setImage("default");			
		}					
		
		if(itemid == 0){
			
			mdb.addMovie(m);
		}
		else
		{
			mdb.updateMovie(m);
		}
		startActivity(new Intent(getApplicationContext(),MainActivity.class));
		finish();
		
	}
	
	public void getImage(View v){
		
		Intent it = new Intent(Intent.ACTION_GET_CONTENT);
		it.setType("image/*");
		startActivityForResult(it, 101);
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
//		if (resultCode == RESULT_OK && requestCode == 101) {
//			imageURI = data.getData();
//			thumb.setImageURI(imageURI);
//			
//		}
//	}

}
