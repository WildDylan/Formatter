package cn.dylan.formatter;

/**
 * 2018/10/18 cn.dylan.formatter
 *
 * @author dylan
 */
public class Point {
	private String mark;
	private String processedMark;

	public Point(String mark, String processedMark) {
		this.mark = mark;
		this.processedMark = processedMark;
	}

	public String getMark() {
		return mark;
	}

	public String getProcessedMark() {
		return processedMark;
	}
}
