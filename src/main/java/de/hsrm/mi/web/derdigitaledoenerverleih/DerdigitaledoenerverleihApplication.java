package de.hsrm.mi.web.derdigitaledoenerverleih;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class DerdigitaledoenerverleihApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(DerdigitaledoenerverleihApplication.class, args);

		String[] beans = ctx.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			if(bean.contains("Controller")){
				System.out.println(bean);
			}	
    }
    }

}
