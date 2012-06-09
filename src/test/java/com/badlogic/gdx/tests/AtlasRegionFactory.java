package com.badlogic.gdx.tests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AtlasRegionFactory {
	
	public static AtlasRegion atlasRegion(Texture texture, int x, int y, int width, int height, int originalWidth, int originalHeight, int offsetX, int offsetY) {
		return atlasRegion(texture, x, y, width, height, originalWidth, originalHeight, offsetX, offsetY, false);
	}
	
	public static AtlasRegion atlasRegion(Texture texture, int x, int y, int width, int height, int originalWidth, int originalHeight, int offsetX, int offsetY, boolean rotate) {
		AtlasRegion region = new AtlasRegion(texture, x, y, width, height);
		region.originalWidth = originalWidth;
		region.originalHeight = originalHeight;
		region.offsetX = offsetX;
		region.offsetY = offsetY;
		region.rotate = rotate;
		return region;
	}

}
