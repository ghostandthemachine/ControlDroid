package com.android.controldroid.widgets;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import com.android.controldroid.surface.ControlDroidSurface;
import com.droidgraph.fx.DGFXShape;
import com.droidgraph.shape.Image;

public class OptionButton extends DGFXShape {

	private Widget parent;

	private float startX, startY;

	private float size;

	private int cellSize;

	private int padding = 2;

	private ControlDroidSurface surface;

	private Animator slideInAnimation;
	private Animator slideOutAnimation;
	
	private long inflateDuration = 400;
	
	private int direction;

	public OptionButton(Widget widget, ControlDroidSurface s, int directionToInflate) {
		parent = widget;
		surface = s;
		cellSize = surface.getGrid().getStepSize();

		size = (cellSize * 2) - (padding * 2);

		startX = parent.getFXNode().getWidth() / 2;
		startY = parent.getFXNode().getHeight() - size;
		
		setTranslateX(startX);
		setTranslateY(startY);
		
		direction = directionToInflate;
		
		this.setShape(new Image("option_button_test.png"));

	}

	private int oppositeDirection(int direction) {
		if (direction == SlideFactory.NORTH) {
			return SlideFactory.SOUTH;
		} else if (direction == SlideFactory.EAST) {
			return SlideFactory.WEST;
		} else if (direction == SlideFactory.SOUTH) {
			return SlideFactory.NORTH;
		} else if (direction == SlideFactory.WEST) {
			return SlideFactory.EAST;
		} else if (direction == SlideFactory.NORTH_EAST) {
			return SlideFactory.SOUTH_WEST;
		} else if (direction == SlideFactory.SOUTH_EAST) {
			return SlideFactory.NORTH_WEST;
		} else if (direction == SlideFactory.SOUTH_WEST) {
			return SlideFactory.NORTH_EAST;
		} else if (direction == SlideFactory.NORTH_WEST) {
			return SlideFactory.SOUTH_EAST;
		}
		return 0;
	}

	public void animateSlideOut() {
		if (slideOutAnimation != null) {
			surface.startAnimation(SlideFactory.buildSlide(this, direction, inflateDuration));
		}
	}

	public void animateSlideIn() {
		if (slideInAnimation != null) {
			surface.startAnimation(SlideFactory.buildSlide(this, oppositeDirection(direction), inflateDuration));
		}
	}

	public int getCellSize() {
		return cellSize;
	}

	public static class SlideFactory {

		public static int NORTH = 0;
		public static int EAST = 1;
		public static int SOUTH = 2;
		public static int WEST = 3;
		public static int NORTH_EAST = 4;
		public static int SOUTH_EAST = 5;
		public static int SOUTH_WEST = 6;
		public static int NORTH_WEST = 7;

		public static Animator buildSlide(OptionButton button, int direction,
				long durationMillis) {
			AnimatorSet set = new AnimatorSet();
			set.setDuration(durationMillis);

			float bsx = 0;
			float bex = 0;
			float bsy = 0;
			float bey = 0;

			if (direction == NORTH) {
				bsy = button.getTranslateY();
				bey = bsy + (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateY", bsy, bey));
			} else if (direction == EAST) {
				bsx = button.getTranslateX();
				bex = bsx + (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateX", bsx, bex));
			} else if (direction == SOUTH) {
				bsy = button.getTranslateY();
				bey = bsy - (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateY", bsy, bey));
			} else if (direction == WEST) {
				bsx = button.getTranslateX();
				bex = bsx - (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateX", bsx, bex));

			} else if (direction == NORTH_EAST) {
				bsx = button.getTranslateX();
				bex = bsx + (button.getCellSize() * 2);

				bsy = button.getTranslateY();
				bey = bsy - (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateY", bsy, bey))
						.with(ObjectAnimator.ofFloat(button, "translateX", bsx,
								bex));

			} else if (direction == SOUTH_EAST) {
				bsx = button.getTranslateX();
				bex = bsx + (button.getCellSize() * 2);

				bsy = button.getTranslateY();
				bey = bsy + (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateY", bsy, bey))
						.with(ObjectAnimator.ofFloat(button, "translateX", bsx,
								bex));

			} else if (direction == SOUTH_WEST) {
				bsx = button.getTranslateX();
				bex = bsx - (button.getCellSize() * 2);

				bsy = button.getTranslateY();
				bey = bsy + (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateY", bsy, bey))
						.with(ObjectAnimator.ofFloat(button, "translateX", bsx,
								bex));

			} else if (direction == NORTH_WEST) {
				bsx = button.getTranslateX();
				bex = bsx - (button.getCellSize() * 2);

				bsy = button.getTranslateY();
				bey = bsy - (button.getCellSize() * 2);
				set.play(ObjectAnimator.ofFloat(button, "translateY", bsy, bey))
						.with(ObjectAnimator.ofFloat(button, "translateX", bsx,
								bex));

			}
			return set;
		}
	}

}
