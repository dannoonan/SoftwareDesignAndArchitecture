package ie.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("ie.demo.mapper")
public class MySpringBootDemo {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootDemo.class, args);
	}
}
