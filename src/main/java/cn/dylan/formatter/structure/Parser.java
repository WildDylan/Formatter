package cn.dylan.formatter.structure;

import cn.dylan.formatter.Template;

/**
 * 2018/10/18 cn.dylan.formatter.structure
 *
 * @author dylan
 */
public interface Parser {

	/**
	 * parse template
	 * @param template object contain template name and content
	 * @param cachable cache utils
	 * @param marked marked utils
	 */
	void parseTemplate(Template template, Cachable cachable, Marked marked);

}
