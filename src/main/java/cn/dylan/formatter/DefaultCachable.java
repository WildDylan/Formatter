package cn.dylan.formatter;

import cn.dylan.formatter.structure.Cachable;
import cn.dylan.formatter.structure.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2018/10/18 cn.dylan.formatter
 *
 * @author dylan
 */
public class DefaultCachable implements Cachable {
	private ConcurrentHashMap<String, List<Point>> templateCache = new ConcurrentHashMap<>(16, 0.8f, 16);
	private ConcurrentHashMap<String, HashMap<String, Field>> fieldCache = new ConcurrentHashMap<>(16, 0.8f, 16);
	private ConcurrentHashMap<String, java.lang.reflect.Field[]> classFieldCache = new ConcurrentHashMap<>(16, 0.8f, 16);

	@Override
	public List<Point> getCacheForTemplate(String templateName) {
		List<Point> points = templateCache.get(templateName);
		if (points != null) {
			return points;
		}
		return new ArrayList<>();
	}

	@Override
	public void upsertCacheForTemplate(String templateName, List<Point> points) {
		templateCache.put(templateName, points);
	}

	@Override
	public void removeCacheForTemplate(String templateName) {
		templateCache.remove(templateName);
	}

	@Override
	public Field getCacheForField(String classField, String className) {
		HashMap<String, Field> fieldHashMap = fieldCache.get(className);
		if (fieldHashMap != null) {
			return fieldHashMap.get(classField);
		}
		return null;
	}

	@Override
	public void setCacheForField(String classField, String className, Field fieldInfo) {
		HashMap<String, Field> fieldHashMap = fieldCache.getOrDefault(className, new HashMap<>(6));
		fieldHashMap.put(classField, fieldInfo);
		fieldCache.put(className, fieldHashMap);
	}

	@Override
	public java.lang.reflect.Field[] getCachedForClassFields(String className) {
		return classFieldCache.get(className);
	}

	@Override
	public void setCacheForClassFields(String className, java.lang.reflect.Field[] fields) {
		classFieldCache.put(className, fields);
	}
}
