package cn.dylan.formatter;

import cn.dylan.formatter.structure.Cachable;
import cn.dylan.formatter.structure.Marked;
import cn.dylan.formatter.structure.Parser;
import cn.dylan.formatter.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/10/18 cn.dylan.formatter
 *
 * @author dylan
 */
public class DefaultParser implements Parser {

	@Override
	public void parseTemplate(Template template, Cachable cachable, Marked marked) {
		// validated template content
		if (StringUtils.notEmpty(template.getName(), template.getContent())) {
			// start parse template content
			String templateContent = template.getContent();
			List<Point> points = findPoint(templateContent, marked);
			// save to cache
			cachable.upsertCacheForTemplate(template.getName(), points);
		}
	}

	private List<Point> findPoint(String content, Marked marked) {
		String markedStart = marked.getMarkedStart();
		String markedEnd = marked.getMarkedEnd();

		if (StringUtils.notEmpty(markedStart, markedEnd) &&
				content.contains(markedStart) && content.contains(markedEnd)) {
			// Start parse, get start point
			return searchPoint(content, markedStart, markedEnd);
		}

		return new ArrayList<>();
	}

	private List<Point> searchPoint(String content, String start, String end) {
		List<Point> points = new ArrayList<>();

		String subString = content;
		while (subString != null) {
			int startPoint = subString.indexOf(start);
			int endPoint = subString.indexOf(end);
			if (startPoint != -1 && endPoint != -1 && startPoint + start.length() < endPoint) {
				String pointedString = subString.substring(startPoint, endPoint + end.length());
				points.add(new Point(pointedString, pointedString.replace(" ", "").replace(start, "").replace(end, "")));
				subString = subString.substring(endPoint + end.length());
			} else {
				subString = null;
			}
		}

		return points;
	}

}
