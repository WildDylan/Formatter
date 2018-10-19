package cn.dylan.formatter.structure;

import java.lang.annotation.*;

/**
 * 2018/10/18 cn.dylan.formatter
 *
 * @author dylan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface Field {

	String name() default "";

}
