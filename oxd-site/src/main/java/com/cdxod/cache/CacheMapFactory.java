package com.cdxod.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheMapFactory {
	private static class cacheHolder {
		public static Map<String, Object> map = new LinkedHashMap<String, Object>();
	}
	
	public static Map<String, Object> getCacheMap() {
		return CacheMapFactory.cacheHolder.map;
	}
}
