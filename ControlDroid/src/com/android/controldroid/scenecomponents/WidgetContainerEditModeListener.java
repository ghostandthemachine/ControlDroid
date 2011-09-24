package com.android.controldroid.scenecomponents;

import com.android.controldroid.surface.ControlDroidSurface;
import com.android.controldroid.widgets.WidgetContainer;
import com.droidgraph.event.DGMotionEvent;

public class WidgetContainerEditModeListener extends WidgetEditModeListener {

	private WidgetContainer parent;
	
	private boolean toggle = false;
	
	public WidgetContainerEditModeListener(WidgetContainer node, ControlDroidSurface s) {
		super(node, s);
		parent = node;
	}

	@Override
	public boolean doubleTap(DGMotionEvent me) {
		if(toggle) {
			toggle = false;
			parent.deflateOptionNodes();
		} else {
			toggle = true;
			parent.inflateOptionNodes();
		}
		return super.doubleTap(me);
	}
	
}
