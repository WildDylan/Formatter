package cn.dylan.formatter.structure;

import cn.dylan.formatter.Point;

import java.util.List;

/**
 * 2018/10/18 cn.dylan.formatter.structure
 *
 * @author dylan
 */
public interface Cachable {

	/**
	 * get cached point for template
	 * @param templateName template name
	 * @return List, contain replace 'Point'
	 */
	List<Point> getCacheForTemplate(String templateName);

	/**
	 * upsert cache
	 * @param templateName template name
	 * @param points all mark points
	 */
	void upsertCacheForTemplate(String templateName, List<Point> points);

	/**
	 * remove cache
	 * @param templateName template name
	 */
	void removeCacheForTemplate(String templateName);

	/**
	 * get cached annotation field
	 * @param classField class field name
	 * @param className class name
	 * @return Field info
	 */
	Field getCacheForField(String classField, String className);

	/**
	 * set cache for field
	 * @param classField class field name
	 * @param className class name
	 * @param fieldInfo Field info
	 */
	void setCacheForField(String classField, String className, Field fieldInfo);

	/**
	 * get class fields
	 * @param className class name
	 * @return field list
	 */
	java.lang.reflect.Field[] getCachedForClassFields(String className);

	/**
	 * set cache fields for class
	 * @param className class name
	 * @param fields fields
	 */
	void setCacheForClassFields(String className, java.lang.reflect.Field[] fields);

}
