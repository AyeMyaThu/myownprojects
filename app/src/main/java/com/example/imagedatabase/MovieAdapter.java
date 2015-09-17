package com.example.imagedatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieAdapter extends BaseAdapter {

	private ArrayList<Movie> al;
	//private ArrayList<Movie> tmpUserList;
	private Context context;
	private String img;
	public MovieAdapter(Context context, ArrayList<Movie> al) {

		super();
		this.context = context;
		this.al = al;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return al.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		long selectedID = Long.parseLong(al.get(position).getId());
		return selectedID;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.row, null);
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context);
		//SharedPreferences pref = PreferenceManager
				//.getDefaultSharedPreferences(context);
		
		TextView title = (TextView) convertView.findViewById(R.id.rtitle);
		title.setText(al.get(position).getTitle());

		String fontSize = sp.getString("fontsize", "20");
		title.setTextSize(Float.parseFloat(fontSize));

		TextView starring = (TextView) convertView.findViewById(R.id.rstarring);
		starring.setText(al.get(position).getStarring());
		starring.setTextSize(Float.parseFloat(fontSize));
		TextView category = (TextView) convertView.findViewById(R.id.rcategory);
		category.setText(al.get(position).getCategory());

		TextView rating = (TextView) convertView.findViewById(R.id.rrate);

		String r = "";
		int red = 0;

		for (int i = 0; i < al.get(position).getRate(); i++) {

			r = r + "*";
			red = red + 25;
		}
		rating.setText(r);
		rating.setTextSize(24);
		rating.setTextColor(Color.rgb(red, 0, 0));

		
		
		
		ImageView thumb = (ImageView) convertView.findViewById(R.id.rimage);
		
		
		if(sp.getBoolean("images",true)){
			if(!al.get(position).getImage().equalsIgnoreCase(img)){
				thumb.setImageURI(Uri.parse(al.get(position).getImage()));//edit
			}else
			
			thumb.setImageResource(R.drawable.ic_launcher);//add
		}else{
			thumb.setVisibility(View.GONE);
		}
		
		
		return convertView;
	}

	

}
