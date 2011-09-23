package com.android.controldroid.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.controldroid.surface.ControlDroidSurface;
import com.droidgraph.scene.DGScene;
import com.droidgraph.util.Shared;

public class ControlDroid extends Activity implements OnClickListener {
	
	private String TAG = "ControlDroid - ";

	private Fragment surf;
	
	public enum Mode {
		EDIT,
		USE
	}
	
	private Mode mMode = Mode.EDIT;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FragmentManager fmanager = getFragmentManager();
		surf = new ControlDroidSurface();

		
		FragmentTransaction ft = fmanager.beginTransaction();
		ft.replace(R.id.surface, surf);
		ft.commit();

		
		ImageButton b = (ImageButton) findViewById(R.id.mode_button);
		b.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {
//		ControlDroidSurface s = (ControlDroidSurface) getFragmentManager().findFragmentById(R.id.surface); 
		ControlDroidSurface s = (ControlDroidSurface) surf;
		DGScene scene = s.getScene();
		if(v == (ImageButton) findViewById(R.id.mode_button)) {
			if(mMode == Mode.EDIT){ 
				Shared.p(TAG, "onClick(), mode = USE");
				mMode = Mode.USE;
				scene.setBackground(0);
			} else {
				Shared.p(TAG, "onClick(), mode = EDIT");
				mMode = Mode.EDIT;
				scene.setBackground(255);
			}
		}
	}
	


}