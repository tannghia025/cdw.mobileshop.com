package com.laptrinhoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;



/*
 * - @Enable yêu cầu boot thêm các bean dựa vào cài đặt classpath, các bean khác và thuộc tính khác nhau
 * 
 * */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { 
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, 
		HibernateJpaAutoConfiguration.class 
	})
public class MobileShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileShopApplication.class, args);
	}

}
