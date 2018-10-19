package cn.dylan.formatter;

import cn.dylan.formatter.structure.Field;
import cn.dylan.formatter.structure.Parseable;

/**
 * 2018/10/18 PACKAGE_NAME
 *
 * @author dylan
 */
public class Person implements Parseable {

	@Field
	private String name;

	@Field(name = "age_test")
	private Integer age;

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
