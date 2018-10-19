import cn.dylan.formatter.Formatter;
import cn.dylan.formatter.Person;
import cn.dylan.formatter.Template;
import cn.dylan.formatter.utils.StringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 2018/10/18 PACKAGE_NAME
 *
 * @author dylan
 */
public class FormatterUnitTest {

	public static void main(String[] args) {
		Template template = new Template("studentTemplate", "Hello, my name is {{ name }}, age is {{ age_test }}.");

		Template t1 = new Template("t1", "Hello, T1 name is {{ name }}, age is {{ age_test }}.");
		Template t2 = new Template("t2", "Hello, T2 name is {{ name }}, age is {{ age_test }}.");
		Template t3 = new Template("t3", "Hello, T3 name is {{ name }}, age is {{ age_test }}.");

		Formatter formatter = new Formatter(template, t1, t2, t3);

		Person person1 = new Person("dylan", 6);
		Person person2 = new Person("jeff", 18);
		Person person3 = new Person("amy", 16);

		// Multiple thread test
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(() -> {
			System.out.println("T1 START");
			long time1 = System.currentTimeMillis();
			// Single thread test
			for (int i = 0; i < 300000; i ++) {
				String result = formatter.parse(person1, "t1");
				if (!result.equals("Hello, T1 name is dylan, age is 6.")) {
					System.out.println("T1 Error");
				}
			}
			System.out.println("T1 COST: " + (System.currentTimeMillis() - time1));
		});

		executorService.execute(() -> {
			System.out.println("T2 START");
			long time2 = System.currentTimeMillis();
			// Single thread test
			for (int i = 0; i < 300000; i ++) {
				String result = formatter.parse(person2, "t2");
				if (!result.equals("Hello, T2 name is jeff, age is 18.")) {
					System.out.println("T2 Error");
				}
			}
			System.out.println("T2 COST: " + (System.currentTimeMillis() - time2));
		});

		executorService.execute(() -> {
			System.out.println("T3 START");
			long time3 = System.currentTimeMillis();
			// Single thread test
			for (int i = 0; i < 300000; i ++) {
				String result = formatter.parse(person3, "t3");
				if (!result.equals("Hello, T3 name is amy, age is 16.")) {
					System.out.println("T3 Error");
				}
			}
			System.out.println("T3 COST: " + (System.currentTimeMillis() - time3));
		});
	}

}
