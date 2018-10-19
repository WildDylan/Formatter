package cn.dylan.formatter;

import cn.dylan.formatter.structure.*;
import cn.dylan.formatter.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 2018/10/18 cn.dylan.formatter
 *
 * @author dylan
 */
public class Formatter implements Format, Initializer {

	private Parser parser = new DefaultParser();
	private Cachable cachable = new DefaultCachable();
	private Marked marked = new DefaultMarked();
	private HashMap<String, Template> templateMap = new HashMap<>(16);

	public String parse(Parseable parseable, String templateName) {
		List<Point> points = getCachable().getCacheForTemplate(templateName);
		Template template = templateMap.get(templateName);
		if (points.size() != 0 && template != null) {
			// 获得接口下的
			Class<?> cls = parseable.getClass();
			String className = cls.getName();
			Field[] fields = getCachable().getCachedForClassFields(className);
			if (fields == null) {
				fields = cls.getDeclaredFields();
				getCachable().setCacheForClassFields(className, fields);
			}
			String content = template.getContent();
			HashMap<String, String> keyValue = new HashMap<>(16);
			if (StringUtils.notEmpty(content)) {
				try {
					for (Field field : fields) {
						field.setAccessible(true);
						String property = field.getName();
						cn.dylan.formatter.structure.Field cacheForField = getCachable().getCacheForField(property, className);
						if (cacheForField == null) {
							cacheForField = field.getAnnotation(cn.dylan.formatter.structure.Field.class);
							getCachable().setCacheForField(property, className, cacheForField);
						}
						if (cacheForField != null) {
							if (StringUtils.notEmpty(cacheForField.name())) {
								property = cacheForField.name();
							}
							Object value = field.get(parseable);
							keyValue.put(property, value.toString());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			for (Point p: points) {
				String value = keyValue.get(p.getProcessedMark());
				if (StringUtils.notEmpty(value)) {
					content = StringUtils.replace(content, p.getMark(), value);
				}
			}
			return content;
		}

		return "";
	}

	public Formatter(Template... templates) {
		regist(templates);
		init();
	}

	@Override
	public Parser getParser() {
		return parser;
	}

	@Override
	public Cachable getCachable() {
		return cachable;
	}

	@Override
	public Marked getMarked() {
		return marked;
	}

	private void init() {
		templateMap.forEach((key, template) -> getParser().parseTemplate(template, getCachable(), getMarked()));
	}

	private void regist(Template... templates) {
		Arrays.asList(templates).forEach(item -> templateMap.put(item.getName(), item));
	}
}
