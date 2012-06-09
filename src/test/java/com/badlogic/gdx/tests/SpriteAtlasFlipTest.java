package com.badlogic.gdx.tests;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;

public class SpriteAtlasFlipTest {

	@Test
	public void shouldHaveSizeBasedOnOriginalSize() {
		Texture texture = org.easymock.EasyMock.createMock(Texture.class);

		expect(texture.getWidth()).andReturn(240).anyTimes();
		expect(texture.getHeight()).andReturn(320).anyTimes();

		replay(texture);

		AtlasRegion region = new AtlasRegion(texture, 0, 0, 100, 120);

		region.originalWidth = 100;
		region.originalHeight = 120;

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(100f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(120f));

		verify(texture);
	}

	@Test
	public void shouldHaveSizeBasedOnOriginalSizeEvenIfPackedSizeDiffers() {
		Texture texture = org.easymock.EasyMock.createMock(Texture.class);

		expect(texture.getWidth()).andReturn(240).anyTimes();
		expect(texture.getHeight()).andReturn(320).anyTimes();

		replay(texture);

		AtlasRegion region = new AtlasRegion(texture, 0, 0, 120, 120);

		region.originalWidth = 200;
		region.originalHeight = 200;

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(200f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(200f));

		verify(texture);
	}

	@Test
	public void shouldReturnSizeWhenWhiteSpaceNoRotation() {
		Texture texture = org.easymock.EasyMock.createMock(Texture.class);

		expect(texture.getWidth()).andReturn(128).anyTimes();
		expect(texture.getHeight()).andReturn(512).anyTimes();

		replay(texture);

		AtlasRegion region = new AtlasRegion(texture, 2, 97, 181, 42);

		region.originalWidth = 200;
		region.originalHeight = 64;
		region.offsetX = 10;
		region.offsetY = 15;

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(200f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(64f));

		verify(texture);
	}

	@Test
	public void shouldReturnSizeWhenWhiteSpaceAndRotation() {
		Texture texture = org.easymock.EasyMock.createMock(Texture.class);

		expect(texture.getWidth()).andReturn(128).anyTimes();
		expect(texture.getHeight()).andReturn(512).anyTimes();

		replay(texture);

		AtlasRegion region = new AtlasRegion(texture, 2, 97, 42, 181);

		region.rotate = true;

		region.originalWidth = 200;
		region.originalHeight = 64;
		region.offsetX = 10;
		region.offsetY = 15;

		AtlasSprite atlasSprite = new AtlasSprite(region);

		assertThat(atlasSprite.getWidth(), IsEqual.equalTo(200f));
		assertThat(atlasSprite.getHeight(), IsEqual.equalTo(64f));

		verify(texture);
	}
}
