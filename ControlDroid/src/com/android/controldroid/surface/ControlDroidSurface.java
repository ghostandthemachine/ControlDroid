package com.android.controldroid.surface;

import processing.core.PApplet;
import android.animation.Animator;
import android.view.MotionEvent;

import com.android.controldroid.app.ControlDroid;
import com.android.controldroid.scenecomponents.SurfaceGrid;
import com.android.controldroid.scenecomponents.TestWidget;
import com.droidgraph.fx.DGFXNode;
import com.droidgraph.scene.DGScene;

public class ControlDroidSurface extends PApplet {

	private String TAG = "ControlDroidSurface - ";
	
	private DGScene scene;

	public SurfaceGrid grid;

	public boolean gridActive = false;

	public boolean snapActive = false;

	public final static int EDIT_MODE = 0;
	public final static int USER_MODE = 1;

	private int mode = EDIT_MODE;
	
	private ControlDroid application;
	
	public ControlDroidSurface(ControlDroid cd) {
		application = cd;
	}

	public void setup() {
		buildScene();
		
		add(new TestWidget(this));
		
	}
	
	private void buildScene() {
		scene = new DGScene(this);
		scene.setBackground(255, 255, 255, 255);
		grid = new SurfaceGrid();
		scene.add(grid);
	}
	
	private void add(DGFXNode node) {		
		scene.add(node);
	}

	public String sketchRenderer() {
		return P3D;
	}

	public void draw() {
		scene.draw();
	}

	public DGScene getScene() {
		return scene;
	}

	public SurfaceGrid getGrid() {
		return grid;
	}

	@Override
	public boolean surfaceTouchEvent(MotionEvent me) {
		scene.handleMotionEvent(me);
		return super.surfaceTouchEvent(me);
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int m) {
		if (m == EDIT_MODE || m == USER_MODE) {
			mode = m;
			if(m == EDIT_MODE) {
				grid.setVisible(true);
			} else if (m == USER_MODE) {
				grid.setVisible(false);
			}
		}
	}
	
	public void setSnapActive(boolean b) {
		snapActive = b;
	}
	
	public boolean isSnapActive() {
		return snapActive;
	}
	
	public void startAnimation(Animator a) {
		application.startAnimation(a);
	}

}
