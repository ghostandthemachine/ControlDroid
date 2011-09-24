package com.android.controldroid.widgets;

import java.util.ArrayList;

import processing.core.PApplet;

import com.android.controldroid.surface.ControlDroidSurface;
import com.droidgraph.event.DGMotionEvent;
import com.droidgraph.fx.DGFXShape;
import com.droidgraph.listener.DGValueListener;
import com.droidgraph.motionlistener.ActionListener;
import com.droidgraph.shape.Rect;
import com.droidgraph.transformation.Vec3f;
import com.droidgraph.util.Shared;

public class Slider extends WidgetContainer {
	
	private String TAG = "Slider - ";
	
	ArrayList<DGValueListener> valueListeners = new ArrayList<DGValueListener>();
	
	DGFXShape valueRect;
	DGFXShape backing;
	
	float sWidth = 20.0f;
	float sHeight = 200.0f;
	
	float valueHeight;
	float valueWidth;
	
	float xOffset = 5.0f;
	float yOffset = 5.0f;
	
	Rect valueRectShape;
	
	float value = 127.0f;
	
	public Slider s = this;
	
	
	public Slider(ControlDroidSurface surface) {
		super(surface);
	}

	public Slider(ControlDroidSurface surface, float width, float height) {
		super(surface);
	}

	@Override
	public void onCreateContainedShapes() {
		backing = new DGFXShape();
		valueRect = new DGFXShape();
		
		sWidth = 40;
		sHeight = 240;
		
		valueHeight = sHeight - (yOffset * 2);
		valueWidth = sWidth - (xOffset * 2);
		
		valueRectShape = new Rect(xOffset, yOffset, valueWidth, valueHeight);
		
		backing.setShape(new Rect(0,0,sWidth,sHeight));
		backing.setFillColor(0,0,150,255);
		
		Shared.p(TAG, "creating slider with dimensions:", 0, 0, sWidth, sHeight);
		
		addMotionListener(new ActionListener(this){
			
			@Override
			public boolean actionDown(DGMotionEvent me){ 
				return super.actionDown(me);
			}
			@Override
			public boolean actionMove(DGMotionEvent me){ 
				Vec3f local = getParent().globalToLocal(me.getX(), me.getY());
				
				float x = xOffset;
				float y = Shared.minMaxF(yOffset, sHeight - (yOffset * 2), local.y);
				
				float height = Shared.minMaxF(0, sHeight - (yOffset * 2), valueHeight - local.y + yOffset);
				
				valueRect.setShape(new Rect(x, y, valueWidth,  height));
				
				value = PApplet.map(local.y + yOffset, sHeight - (yOffset * 2), yOffset, 0.0f, 127.0f);
				updateListeners(value);
				
				return super.actionMove(me);
			}
		});
		add(backing);
		
		valueRect.setShape(valueRectShape);
		valueRect.setFillColor(0,255,0,255);
		add(valueRect);
	}

	public void addValueListener(DGValueListener listener) {
		valueListeners.add(listener);
	}
	
	private void updateListeners(float value) {
		for(DGValueListener listener : valueListeners) {
			listener.value(value);
		}
	}

}
