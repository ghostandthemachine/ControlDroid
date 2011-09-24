package com.android.controldroid.scenecomponents;

import com.droidgraph.fx.DGFXGroup;
import com.droidgraph.fx.DGFXShape;
import com.droidgraph.shape.Line;
import com.droidgraph.util.Shared;

public class SurfaceGrid extends DGFXGroup {
	
	private boolean DEBUG = false;
	private String TAG = "SurfaceGrid - ";
	
	private int stepSize = 20;
	
	private int numWidthSteps;
	private int numHeightSteps;
	
	private float width;
	private float height;
	
	public SurfaceGrid() {
		
		width = Shared.pApplet.width;
		height = Shared.pApplet.height;
		
		numWidthSteps = (int) (width / stepSize);
		numHeightSteps = (int) (height / stepSize);
		
		if(DEBUG) {
			Shared.p(TAG, "width:", width, "width steps:", numWidthSteps, "height:", height, "height steps:", numHeightSteps);
		}
			
		float x1 = 0;
		float y1 = 0;
		float x2 = 0;
		float y2 = 0;
		
		// create vertical lines
		for(int i = 0; i <= numWidthSteps; i++) {
			
			x1 = i * stepSize;
			y1 = 0;
			x2 = x1;
			y2 = height;
			
			DGFXShape line = new DGFXShape();
			line.setStrokeColor(0, 0, 75, 60);
			line.setStrokeAndFill(DGFXShape.STROKE);
			line.setShape(new Line(x1, y1, x2, y2));

			add(line);
		}
		
		// create horizontal lines
		for(int j = 0; j <= numHeightSteps; j++) {
			
			x1 = 0;
			y1 = j * stepSize;
			x2 = width;
			y2 = y1;
			
			DGFXShape line = new DGFXShape();
			line.setStrokeColor(0, 0, 75, 60);
			line.setStrokeAndFill(DGFXShape.STROKE);
			line.setShape(new Line(x1, y1, x2, y2));

			add(line);
		}
		
		
	}
	
	public int snapPoint(float p) {
		int tx = (int) p;
		int tm = tx % stepSize;
		if(tm >= stepSize / 2) {
			return tx - tm + stepSize;
		} else {
			return tx - tm;
		}
	}

	public int getStepSize() {
		return stepSize;
	}
}
