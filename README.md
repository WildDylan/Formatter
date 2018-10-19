# Formatter
Simple Mustache template, only with '{{' and '}}'

=

Simple Mustache, just with start and end point, default was `{{` and `}}`,

e.g: Hi, my name is {{ template }} and come from {{ country }}, {{ age }} years old.

now, with an object implement `Parseable` interface:

```java
public class Student implements Parseable {

	@Field(name = "template")
	private String name;

	@Field
	private String country;

	@Field
	private Integer age;

	@Field
	private String favour;

	public Student(String name, String country, Integer age) {
		this.name = name;
		this.country = country;
		this.age = age;
	}
}
```

then, use this way: 

```java
Template template = new Template("tp_name", "Hi, my name is {{ template }} and come from {{ country }}, {{ age }} years old.");
Formatter formatter = new Formatter(template);
Student student = new Student("dylan", "china", 24);
System.out.println(formatter.parse(student, "tp_name"));
```

> {{ template }} use @Field annotation mapped to property name 

you'll get result like this:

Hi, my name is dylan and come from china, 24 years old.

and, benchmark:

better than 500,000 parse per seconds. you can use multiple thread test this framework.
