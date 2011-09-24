package com.android.controldroid.widgets;

import java.util.ArrayList;

import com.android.controldroid.scenecomponents.WidgetEditModeListener;
import com.android.controldroid.surface.ControlDroidSurface;
import com.droidgraph.event.DGMotionEvent;
import com.droidgraph.fx.DGFXNode;
import com.droidgraph.fx.DGFXShape;
import com.droidgraph.motionlistener.MotionListener;
import com.droidgraph.util.Shared;

public class WidgetShape extends DGFXShape implements Widget {
	
	private String TAG = "Widget - ";

	private ControlDroidSurface surface;

	private WidgetEditModeListener editListener;
	
	private ArrayList<MotionListener> editListeners = new ArrayList<MotionListener>();

	public WidgetShape(ControlDroidSurface surface) {
		this.surface = surface;

		editListener = new WidgetEditModeListener(this, surface);
		Shared.p(TAG, "adding edit listener", editListener);
		addEditModeMotionListener(editListener);

	}

	private void addEditModeMotionListener(WidgetEditModeListener editModeWidgetListener) {
		editListeners.add(editModeWidgetListener);
	}

	@Override
	public void handleMotionEvent(DGMotionEvent me) {
		Shared.p("Widget - ", me, surface.getMode());
		if (surface.getMode() == ControlDroidSurface.EDIT_MODE) {
			for (MotionListener ml : editListeners) {
				ml.handleMotionEvent(me);
			}
		} else if (surface.getMode() == ControlDroidSurface.USER_MODE) {
			super.handleMotionEvent(me);
		}
	}
	
	public DGFXNode getFXNode() {
		return this;
	}

	public void deflateOptionNodes() {
		
	}

	public void inflateOptionNodes() {
		// TODO Auto-generated method stub
		
	}
}
