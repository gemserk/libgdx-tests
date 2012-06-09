package com.badlogic.gdx.tests;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.core.IsEqual;

import com.badlogic.gdx.math.Rectangle;

public class RectangleMatcher extends BaseMatcher<Rectangle> {
	
	private final Rectangle rectangle;

	public RectangleMatcher(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public boolean matches(Object item) {
		if (!(item instanceof Rectangle))
		return false;
		
		Rectangle otherRectangle = (Rectangle) item;
		
		if (!IsEqual.equalTo(rectangle.x).matches(otherRectangle.x))
			return false;
		
		if (!IsEqual.equalTo(rectangle.y).matches(otherRectangle.y))
			return false;

		if (!IsEqual.equalTo(rectangle.width).matches(otherRectangle.width))
			return false;

		if (!IsEqual.equalTo(rectangle.height).matches(otherRectangle.height))
			return false;

		return true;
	}

	@Override
	public void describeTo(Description description) {
		
	}
	
	public static RectangleMatcher isEqualRectangle(Rectangle rectangle) {
		return new RectangleMatcher(rectangle);
	}
	
}