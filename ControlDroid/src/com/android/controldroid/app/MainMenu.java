package com.android.controldroid.app;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.droidgraph.util.Shared;

public class MainMenu extends Fragment {

	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		
		return inflater.inflate(R.layout.menu, container);
	}
	
	
	public static class ModeButton extends ImageButton {

		public ModeButton(Context context) {
			super(context);
		}
		
		
		public void toggleMode(View view) {
			Shared.p("Toggle Mode mother fucker");
		}
		
	}
	
}
