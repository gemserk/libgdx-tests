package com.badlogic.gdx.tests;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteAtlasFlipTest {

	private Texture texture128x512;

	@Before
	public void setup() {
		texture128x512 = org.easymock.EasyMock.createMock(Texture.class);
		expect(texture128x512.getWidth()).andReturn(128).anyTimes();
		expect(texture128x512.getHeight()).andReturn(512).anyTimes();
		replay(texture128x512);
	}
	
	@Test
	public void shouldHaveSizeBasedOnOriginalSize() {
		AtlasRegion region = new AtlasRegion(texture128x512, 0, 0, 100, 120);

		region.originalWidth = 100;
		region.originalHeight = 120;

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(100f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(120f));
	}

	@Test
	public void shouldHaveSizeBasedOnOriginalSizeEvenIfPackedSizeDiffers() {
		AtlasRegion region = new AtlasRegion(texture128x512, 0, 0, 120, 120);

		region.originalWidth = 200;
		region.originalHeight = 200;

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(200f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(200f));
	}

	@Test
	public void shouldReturnSizeWhenWhiteSpaceNoRotation() {
		AtlasRegion region = atlasRegion(texture128x512, 2, 97, 181, 42, 200, 64, 10, 15);

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(200f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(64f));
	}

	@Test
	public void shouldReturnSizeWhenWhiteSpaceAndRotation() {
		AtlasRegion region = new AtlasRegion(texture128x512, 2, 97, 42, 181);

		region.rotate = true;

		region.originalWidth = 200;
		region.originalHeight = 64;
		region.offsetX = 10;
		region.offsetY = 15;

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(200f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(64f));
	}

	@Test
	public void shouldReturnSamePositionWhenFlippedHorizontally() {
		AtlasRegion region = atlasRegion(texture128x512, 2, 97, 181, 42, 200, 64, 10, 15);

		AtlasSprite sprite = new AtlasSprite(region);
		
		sprite.setPosition(50f, 70f);
		sprite.setOrigin(65f, 85f);
		sprite.flip(true, false);
		assertThat(sprite.getX(), IsEqual.equalTo(50f));
		assertThat(sprite.getY(), IsEqual.equalTo(70f));
		assertThat(sprite.getOriginX(), IsEqual.equalTo(65f));
		assertThat(sprite.getOriginY(), IsEqual.equalTo(85f));
	}

	@Test
	public void shouldReturnSamePositionWhenFlippedVertically() {
		AtlasRegion region = atlasRegion(texture128x512, 2, 97, 181, 42, 200, 64, 10, 15);

		AtlasSprite sprite = new AtlasSprite(region);
		
		sprite.setPosition(50f, 70f);
		sprite.setOrigin(65f, 85f);
		sprite.flip(false, true);
		assertThat(sprite.getX(), IsEqual.equalTo(50f));
		assertThat(sprite.getY(), IsEqual.equalTo(70f));
		assertThat(sprite.getOriginX(), IsEqual.equalTo(65f));
		assertThat(sprite.getOriginY(), IsEqual.equalTo(85f));
	}
	
	@Test
	public void bugBoundingRectangleShouldNotChangeWhenFlippingHorizontally() {
		AtlasRegion region = atlasRegion(texture128x512, 2, 97, 181, 42, 200, 64, 10, 15);
		AtlasSprite sprite = new AtlasSprite(region);
		sprite.setPosition(50f, 70f);
		sprite.setOrigin(65f, 85f);
		sprite.flip(true, false);
		assertThat(sprite.getBoundingRectangle(), RectangleMatcher.isEqualRectangle(new Rectangle(50f, 70f, 200f, 64f)));
	}

	public AtlasRegion atlasRegion(Texture texture, int x, int y, int width, int height, int originalWidth, int originalHeight, int offsetX, int offsetY) {
		return atlasRegion(texture, x, y, width, height, originalWidth, originalHeight, offsetX, offsetY, false);
	}
	
	public AtlasRegion atlasRegion(Texture texture, int x, int y, int width, int height, int originalWidth, int originalHeight, int offsetX, int offsetY, boolean rotate) {
		AtlasRegion region = new AtlasRegion(texture, x, y, width, height);
		region.originalWidth = originalWidth;
		region.originalHeight = originalHeight;
		region.offsetX = offsetX;
		region.offsetY = offsetY;
		region.rotate = rotate;
		return region;
	}
}
