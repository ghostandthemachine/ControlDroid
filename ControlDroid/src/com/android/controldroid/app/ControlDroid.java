package com.android.controldroid.app;

import android.animation.Animator;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.controldroid.surface.ControlDroidSurface;
import com.droidgraph.scene.DGScene;
import com.droidgraph.util.Shared;

public class ControlDroid extends Activity implements OnClickListener {

	private String TAG = "ControlDroid - ";

	private ControlDroidSurface surface;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FragmentManager fmanager = getFragmentManager();
		surface = new ControlDroidSurface(this);

		FragmentTransaction ft = fmanager.beginTransaction();
		ft.replace(R.id.surface, surface);
		ft.commit();

		ImageButton b = (ImageButton) findViewById(R.id.mode_button);
		b.setOnClickListener(this);

		b = (ImageButton) findViewById(R.id.build_button);
		b.setOnClickListener(this);

		b = (ImageButton) findViewById(R.id.snap_button);
		b.setOnClickListener(this);

		b = (ImageButton) findViewById(R.id.grid_button);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		DGScene scene = ((ControlDroidSurface) surface).getScene();
		Shared.p(TAG, "onClick(), view =", v);
		if (v == (ImageButton) findViewById(R.id.mode_button)) {
			if (surface.getMode() == ControlDroidSurface.EDIT_MODE) {
				Shared.p(TAG, "onClick(), mode = USER");
				surface.setMode(ControlDroidSurface.USER_MODE);
				scene.setBackground(0);
				TextView tv = (TextView) findViewById(R.id.mode_label);
				tv.setText("user");
			} else {
				Shared.p(TAG, "onClick(), mode = EDIT");
				surface.setMode(ControlDroidSurface.EDIT_MODE);
				scene.setBackground(255);
				TextView tv = (TextView) findViewById(R.id.mode_label);
				tv.setText("edit");
			}
		} else if (v == (ImageButton) findViewById(R.id.snap_button)) {
			if (surface.isSnapActive()) {
				Shared.p(TAG, "onClick(), snap mode = off");
				ImageButton b = (ImageButton) findViewById(R.id.snap_button);
				b.setImageResource(android.R.drawable.ic_secure);
				surface.setSnapActive(false);
			} else {
				Shared.p(TAG, "onClick(), snap mode = on");
				ImageButton b = (ImageButton) findViewById(R.id.snap_button);
				b.setImageResource(android.R.drawable.ic_partial_secure);
				surface.setSnapActive(true);
			}
		}

	}
	
	public void startAnimation(Animator a) {
		final Animator animation = a;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				animation.start();
			}
		};
		this.runOnUiThread(r);
	}


}