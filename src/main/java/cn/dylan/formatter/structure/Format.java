package cn.dylan.formatter.structure;

/**
 * 2018/10/18 cn.dylan.formatter
 *
 * @author dylan
 */
public interface Format {

	/**
	 * get parser for formatter
	 * @return Parser
	 */
	Parser getParser();

	/**
	 * get template cache util for formatter
	 * @return CacheAble
	 */
	Cachable getCachable();

	/**
	 * get Marked ( check point ) for formatter, like {{ }}, << >>, can be modified
	 * @return Marked
	 */
	Marked getMarked();

}
