package com.android.controldroid.scenecomponents;

import com.android.controldroid.surface.ControlDroidSurface;
import com.droidgraph.event.DGMotionEvent;
import com.droidgraph.fx.DGFXNode;
import com.droidgraph.motionlistener.GestureListener;

public class WidgetEditModeListener extends GestureListener {

	DGFXNode widget;
	ControlDroidSurface surface;
	
	public WidgetEditModeListener(DGFXNode node, ControlDroidSurface s) {
		super(node);
		this.widget = node;
		this.surface = s;
	}

	public boolean drag(DGMotionEvent me) {
		widget.translateBy(-me.getVelocityX(), -me.getVelocityY());
		return super.drag(me);
	}

	@Override
	public boolean actionUp(DGMotionEvent me) {
		super.actionUp(me);
		float x = widget.getTranslateX();
		float y = widget.getTranslateY();
		widget.setTranslation(surface.getGrid().snapPoint(x),surface.getGrid().snapPoint(y));
		return true;
	}
	

}
