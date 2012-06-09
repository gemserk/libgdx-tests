package com.badlogic.gdx.issues;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class GenerateTexturesMain {
	
	public static void main(String[] args) {
		Settings settings = new Settings();
		settings.alias = true;
		settings.stripWhitespace = true;
		settings.alphaThreshold = 0;
		settings.defaultFilterMag = TextureFilter.Linear;
		settings.defaultFilterMin = TextureFilter.Linear;
		settings.defaultFormat = Format.RGBA8888;
		settings.duplicatePadding=true;
		settings.incremental = false;
		
		settings.maxWidth = 128;
		settings.maxHeight = 512;
		settings.minHeight = 64;
		settings.minWidth = 128;
		
		settings.ignoreBlankImages = true;
		settings.edgePadding = true;
		settings.padding = 2;
		settings.ignoreBlankImages= false;
		settings.pot = true;
		
		settings.rotate = true;
		
		TexturePacker.process(settings, "sourceassets/images/", "data/test/");
	}
}
