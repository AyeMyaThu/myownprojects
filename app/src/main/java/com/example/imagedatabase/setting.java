package com.example.imagedatabase;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class setting extends PreferenceActivity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.setting);
}
}
