package com.badlogic.gdx.tests.utils;

import static org.junit.Assert.assertThat;

import java.util.Random;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectIntMap;

public class ObjectIntMapTest {

	@Test
	public void bugWhenStashContainsKey() {
		ObjectIntMap<Integer> objectIntMap = new ObjectIntMap<Integer>();

		MathUtils.random = new Random(10);
		
		int[] keys = {30314, 71946, 113578, 155210};

		for (int i = 0; i < keys.length; i++) 
			objectIntMap.put(new Integer(keys[i]), keys[i]);
		
		objectIntMap.put(new Integer(keys[3]), keys[3]);
		assertThat(objectIntMap.size, IsEqual.equalTo(keys.length));
	}

}
