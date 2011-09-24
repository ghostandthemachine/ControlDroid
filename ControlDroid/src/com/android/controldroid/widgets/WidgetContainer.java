package com.android.controldroid.widgets;

import java.util.ArrayList;

import com.android.controldroid.scenecomponents.WidgetContainerEditModeListener;
import com.android.controldroid.scenecomponents.WidgetEditModeListener;
import com.android.controldroid.surface.ControlDroidSurface;
import com.droidgraph.event.DGMotionEvent;
import com.droidgraph.fx.DGFXGroup;
import com.droidgraph.fx.DGFXNode;
import com.droidgraph.motionlistener.MotionListener;

public class WidgetContainer extends DGFXGroup implements Widget {
	private String TAG = "WidgetContainer - ";

	private ControlDroidSurface surface;

	private WidgetContainerEditModeListener editListener;

	private ArrayList<MotionListener> editListeners = new ArrayList<MotionListener>();
	
	private OptionButton connect;
	private OptionButton edit;
	
	public WidgetContainer(ControlDroidSurface surface) {
		this.surface = surface;

		onCreateContainedShapes();
		buildOptionNodes();

		editListener = new WidgetContainerEditModeListener(this, surface);
		addEditModeMotionListener(editListener);
	}
	
	public void onCreateContainedShapes() {
		
	}
	
	public ControlDroidSurface getSurface() {
		return surface;
	}
	
	private void buildOptionNodes() {
		edit = new OptionButton(this, surface, OptionButton.SlideFactory.SOUTH_WEST);
		connect = new OptionButton(this, surface, OptionButton.SlideFactory.SOUTH_EAST);
		
		add(edit);
		add(connect);
	}

	private void addEditModeMotionListener(
			WidgetEditModeListener editModeWidgetListener) {
		editListeners.add(editModeWidgetListener);
	}

	@Override
	public void handleMotionEvent(DGMotionEvent me) {
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
		edit.animateSlideIn();
		connect.animateSlideIn();
	}

	public void inflateOptionNodes() {
		edit.animateSlideOut();
		connect.animateSlideOut();
	}

}
