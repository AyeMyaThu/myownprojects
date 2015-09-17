package com.example.imagedatabase;

import java.text.BreakIterator;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends Activity implements OnItemClickListener{	
	
	ListView lv;	
	MovieDatabase mdb;
	MovieAdapter adapter;
	
	
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
       
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdb = new MovieDatabase(getApplicationContext());
        lv = (ListView) findViewById(R.id.listView1);   
        lv.setOnItemClickListener(this);
        registerForContextMenu(lv);       
        init();
    }
    
    private void init()
    {
    	ArrayList<Movie> al = mdb.getAllMovie();
    	adapter = new MovieAdapter(getApplicationContext(), al);
    	lv.setAdapter(adapter);
    }      
   @Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	init();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item)
    {
		switch (item.getItemId()) {
		case R.id.add:
			startActivity(new Intent(getApplicationContext(), AddMovie.class));
			break;
		case R.id.setting:
			startActivity(new Intent(getApplicationContext(), setting.class));
			break;

		}
		
    	
    	return true;
    	
    }
 
  
    
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	getMenuInflater().inflate(R.menu.context, menu);
    }
    
    public boolean onContextItemSelected(MenuItem item)
    {
    	
    	AdapterView.AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	final long selectedID=info.id;
    	switch(item.getItemId())
    	{
	    	case R.id.edit:
	 
	    		Intent it = new Intent(getApplicationContext(), AddMovie.class);
	    		it.putExtra("itemid", info.id);
	    		startActivity(it);
	    		
	    		break;
	    		
	    	case R.id.delete:	
	    		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
	        	
	        	
	        	dialog.setTitle("Confirmation");
	        	dialog.setMessage("Are u sure to delete?");
	        	
	        	
	        	
	        	dialog.setPositiveButton("Yes", new OnClickListener() {
	    			
	    			@Override
	    			public void onClick(DialogInterface dialog, int which) {
	    				// TODO Auto-generated method stub
	    				
	    			
	    				mdb.deleteMovie(selectedID);
	    				init();
	    				
	    				
	    			}
	    		});
	        	dialog.setNegativeButton("No", null);
	        	dialog.show();
	    		
	    		//confirmdlg(info.id);	    		
	    		break;
    	}
    	
    	return true;
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		/*Intent it=new Intent(getApplicationContext(), AddMovie.class);
		it.putExtra("id", arg2+"");
		startActivity(it);*/
		
	}
    
}
