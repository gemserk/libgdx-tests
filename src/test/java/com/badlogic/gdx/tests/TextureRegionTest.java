package com.badlogic.gdx.tests;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionTest {

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
		TextureRegion textureRegion = new TextureRegion(texture128x512, 10, 15, 30, 45);
		assertThat(textureRegion.getRegionWidth(), IsEqual.equalTo(30));
		assertThat(textureRegion.getRegionHeight(), IsEqual.equalTo(45));
		assertThat(textureRegion.getRegionX(), IsEqual.equalTo(10));
		assertThat(textureRegion.getRegionY(), IsEqual.equalTo(15));
		assertThat(textureRegion.getU(), IsEqual.equalTo(10f / 128f));
		assertThat(textureRegion.getV(), IsEqual.equalTo(15f / 512f));
		assertThat(textureRegion.getU2(), IsEqual.equalTo(40f / 128f));
		assertThat(textureRegion.getV2(), IsEqual.equalTo(60f / 512f));
	}
	
	@Test
	public void testAllGetValuesWhenFlippedHorizontally() {
		TextureRegion textureRegion = new TextureRegion(texture128x512, 10, 15, 30, 45);
		textureRegion.flip(true, false);
		assertThat(textureRegion.getRegionWidth(), IsEqual.equalTo(30));
		assertThat(textureRegion.getRegionHeight(), IsEqual.equalTo(45));
		assertThat(textureRegion.getRegionX(), IsEqual.equalTo(40));
		assertThat(textureRegion.getRegionY(), IsEqual.equalTo(15));
		assertThat(textureRegion.getU(), IsEqual.equalTo(40f / 128f));
		assertThat(textureRegion.getV(), IsEqual.equalTo(15f / 512f));
		assertThat(textureRegion.getU2(), IsEqual.equalTo(10f / 128f));
		assertThat(textureRegion.getV2(), IsEqual.equalTo(60f / 512f));
	}
	
	@Test
	public void testAllGetValuesWhenFlippedVertically() {
		TextureRegion textureRegion = new TextureRegion(texture128x512, 10, 15, 30, 45);
		textureRegion.flip(false, true);
		assertThat(textureRegion.getRegionWidth(), IsEqual.equalTo(30));
		assertThat(textureRegion.getRegionHeight(), IsEqual.equalTo(45));
		assertThat(textureRegion.getRegionX(), IsEqual.equalTo(10));
		assertThat(textureRegion.getRegionY(), IsEqual.equalTo(60));
		assertThat(textureRegion.getU(), IsEqual.equalTo(10f / 128f));
		assertThat(textureRegion.getV(), IsEqual.equalTo(60f / 512f));
		assertThat(textureRegion.getU2(), IsEqual.equalTo(40f / 128f));
		assertThat(textureRegion.getV2(), IsEqual.equalTo(15f / 512f));
	}
}
