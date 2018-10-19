package cn.dylan.formatter.structure;

import cn.dylan.formatter.configuration.Configuration;

/**
 * 2018/10/18 cn.dylan.formatter.structure
 *
 * @author dylan
 */
public interface Marked {

	/**
	 * get Marked-Point-Starter, default value was '{{'
	 * @return String
	 */
	default String getMarkedStart() {
		return Configuration.MARKED_START;
	}

	/**
	 * get Marked-Point-End, default value was '}}'
	 * @return String
	 */
	default String getMarkedEnd() {
		return Configuration.MARKED_END;
	}

}
