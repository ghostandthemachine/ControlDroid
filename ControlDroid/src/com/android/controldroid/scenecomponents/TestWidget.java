package com.android.controldroid.scenecomponents;

import com.android.controldroid.surface.ControlDroidSurface;
import com.android.controldroid.widgets.WidgetContainer;
import com.droidgraph.fx.DGFXShape;
import com.droidgraph.shape.Rect;

public class TestWidget extends WidgetContainer {

	public TestWidget(ControlDroidSurface s) {
		super(s);
	}

	public void onCreateContainedShapes() {
		DGFXShape shape = new DGFXShape();
		shape.setShape(new Rect(0,0,200,200));
		shape.setFillColor(0, 255, 0);
		add(shape);
	}
}
