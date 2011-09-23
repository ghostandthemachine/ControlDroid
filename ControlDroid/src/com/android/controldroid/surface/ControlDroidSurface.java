package com.android.controldroid.surface;

import com.droidgraph.scene.DGScene;

import processing.core.PApplet;

public class ControlDroidSurface extends PApplet {

	private DGScene scene;
	
	public void setup() {

		scene = new DGScene(this);
		scene.setBackground(255, 255, 255, 255);
		
	}
	
	public String sketchRenderer() {
		return P3D;
	}

	public void draw() {
		background(255);
		scene.draw();
	}

	
	public DGScene getScene() {
		return scene;
	}
}
