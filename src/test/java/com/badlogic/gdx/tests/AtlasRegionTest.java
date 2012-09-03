package com.badlogic.gdx.tests;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AtlasRegionTest {

	private Texture texture128x512;

	@Before
	public void setup() {
		texture128x512 = org.easymock.EasyMock.createMock(Texture.class);
		expect(texture128x512.getWidth()).andReturn(128).anyTimes();
		expect(texture128x512.getHeight()).andReturn(512).anyTimes();
		replay(texture128x512);
	}

	@Test
	public void testAllGetValues() {
		AtlasRegion atlasRegion = AtlasRegionFactory.atlasRegion(texture128x512, 10, 15, 30, 45, 30, 45, 10, 15);
		assertThat(atlasRegion.getRegionWidth(), IsEqual.equalTo(30));
		assertThat(atlasRegion.getRegionHeight(), IsEqual.equalTo(45));
		assertThat(atlasRegion.getRegionX(), IsEqual.equalTo(10));
		assertThat(atlasRegion.getRegionY(), IsEqual.equalTo(15));
		assertThat(atlasRegion.getU(), IsEqual.equalTo(10f / 128f));
		assertThat(atlasRegion.getV(), IsEqual.equalTo(15f / 512f));
		assertThat(atlasRegion.getU2(), IsEqual.equalTo(40f / 128f));
		assertThat(atlasRegion.getV2(), IsEqual.equalTo(60f / 512f));
		assertThat(atlasRegion.offsetX, IsEqual.equalTo(10f));
		assertThat(atlasRegion.offsetY, IsEqual.equalTo(15f));
	}

	@Test
	public void shouldReturnInvertedTextureCoordinatesWhenFlippedHorizontally() {
		AtlasRegion atlasRegion = AtlasRegionFactory.atlasRegion(texture128x512, 10, 15, 30, 45, 30, 45, 10, 15);
		atlasRegion.flip(true, false);
		assertThat(atlasRegion.getRegionWidth(), IsEqual.equalTo(30));
		assertThat(atlasRegion.getRegionHeight(), IsEqual.equalTo(45));
		assertThat(atlasRegion.getRegionX(), IsEqual.equalTo(40));
		assertThat(atlasRegion.getRegionY(), IsEqual.equalTo(15));
		assertThat(atlasRegion.getU(), IsEqual.equalTo(40f / 128f));
		assertThat(atlasRegion.getV(), IsEqual.equalTo(15f / 512f));
		assertThat(atlasRegion.getU2(), IsEqual.equalTo(10f / 128f));
		assertThat(atlasRegion.getV2(), IsEqual.equalTo(60f / 512f));
		assertThat(atlasRegion.offsetX, IsEqual.equalTo(-10f));
		assertThat(atlasRegion.offsetY, IsEqual.equalTo(15f));
	}
	
	@Test
	public void shouldReturnInvertedTextureCoordinatesWhenFlippedVertically() {
		AtlasRegion atlasRegion = AtlasRegionFactory.atlasRegion(texture128x512, 10, 15, 30, 45, 30, 45, 10, 15);
		atlasRegion.flip(false, true);
		assertThat(atlasRegion.getRegionWidth(), IsEqual.equalTo(30));
		assertThat(atlasRegion.getRegionHeight(), IsEqual.equalTo(45));
		assertThat(atlasRegion.getRegionX(), IsEqual.equalTo(10));
		assertThat(atlasRegion.getRegionY(), IsEqual.equalTo(60));
		assertThat(atlasRegion.getU(), IsEqual.equalTo(10f / 128f));
		assertThat(atlasRegion.getV(), IsEqual.equalTo(60f / 512f));
		assertThat(atlasRegion.getU2(), IsEqual.equalTo(40f / 128f));
		assertThat(atlasRegion.getV2(), IsEqual.equalTo(15f / 512f));
		assertThat(atlasRegion.offsetX, IsEqual.equalTo(10f));
		assertThat(atlasRegion.offsetY, IsEqual.equalTo(-15f));
	}
	
	@Test
	public void shouldAllowMultipleFlips() {
		AtlasRegion atlasRegion = AtlasRegionFactory.atlasRegion(texture128x512, 10, 15, 30, 45, 30, 45, 10, 15);
		atlasRegion.flip(true, false);
		assertThat(atlasRegion.offsetX, IsEqual.equalTo(-10f));
		atlasRegion.flip(true, false);
		assertThat(atlasRegion.offsetX, IsEqual.equalTo(10f));
		atlasRegion.flip(true, false);
		assertThat(atlasRegion.offsetX, IsEqual.equalTo(-10f));
	}
	
	
}
